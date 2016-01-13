
package com.bitbyterstudios.m3c;

import com.bitbyterstudios.m3c.packet_handler.receiving.*;
import com.bitbyterstudios.m3c.packet_handler.receiving.ReceivingPacket;
import com.bitbyterstudios.m3c.packet_handler.sending.EncryptionResponse01;
import com.bitbyterstudios.m3c.packet_handler.sending.HandShake00;
import com.bitbyterstudios.m3c.packet_handler.sending.LoginStart00;
import com.bitbyterstudios.m3c.packets.receiving.*;
import com.bitbyterstudios.m3c.packets.receiving.Chat02;
import com.bitbyterstudios.m3c.packets.receiving.Health06;
import com.bitbyterstudios.m3c.packets.receiving.HeldItemChange09;
import com.bitbyterstudios.m3c.packets.receiving.JoinGame01;
import com.bitbyterstudios.m3c.packets.receiving.KeepAlive00;
import com.bitbyterstudios.m3c.packets.receiving.PlayerPositionLook08;
import com.bitbyterstudios.m3c.packets.receiving.Respawn07;
import com.bitbyterstudios.m3c.packets.receiving.SpawnPosition05;
import com.bitbyterstudios.m3c.packets.receiving.Time03;
import com.bitbyterstudios.m3c.packets.sending.SendingPacket;
import com.bitbyterstudios.m3c.util.CryptoHelper;
import com.bitbyterstudios.m3c.util.Utilities;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import javax.crypto.SecretKey;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

public class ServerHandler {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Client client;
    private ClientData data;

    private HashMap<Integer, ReceivingPacket> login;
    private HashMap<Integer, Class<? extends com.bitbyterstudios.m3c.packets.receiving.ReceivingPacket>> packets;
    private Collection<SendingPacket> packetsToSend;

    private int compressionThreshold;

    public ServerHandler(Client client, ClientData data) {
        this.client = client;
        this.data = data;
    }

    public void init(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            packetsToSend = new ArrayList<>();
            loadPackets();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() throws IOException {
        if (socket == null) {
            init("localhost", 25565);
            Client.getLogger().warning("Init with localhost:25565 as not done before!");
        }

        if (handleLogin()) {
            Client.getLogger().info("Successfully logged in!");
        } else {
            Client.getLogger().severe("You got kicked!");
            return;
        }

        int unknown = 0;
        while (true) {
            ByteBuffer buff;
            int len;

            if (compressionThreshold > 0) {
                int packet_length = readVarInt(in); //packet length
                len = readVarInt(in); //length of uncompressed
                packet_length -= Utilities.getVarIntWidth(len);

                byte[] raw_packet = new byte[packet_length];
                in.readFully(raw_packet);

                if (len > 0) {
                    try {
                        buff = ByteBuffer.wrap(Utilities.decompress(raw_packet));
                    } catch (DataFormatException e) {
                        e.printStackTrace();
                        continue;
                    }
                } else {
                    buff = ByteBuffer.wrap(raw_packet);
                    //let's "fix" len for debug output below
                    len = packet_length - 1;
                }
            } else {
                len = readVarInt(in);
                byte[] raw = new byte[len];
                in.readFully(raw);
                buff = ByteBuffer.wrap(raw);
            }

            int type = readVarInt(buff);
            if (packets.get(type) == null) {
                if (unknown > 3) {
                    return;
                }
                handleUnknown(buff, type, len);
                unknown++;
            } else {
                logReceivedPacket(packets.get(type), type, len);

                Class<? extends com.bitbyterstudios.m3c.packets.receiving.ReceivingPacket> packet_class = packets.get(type);
                com.bitbyterstudios.m3c.packets.receiving.ReceivingPacket packet = null;
                try {
                    packet = packet_class.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                packet.handle(buff, this);
                getClient().getPluginManager().callListeners(packet, this);
            }

            for (SendingPacket packet : packetsToSend) {
                Client.getLogger().finest("sending packet " + packet.getClass().getSimpleName());
                send(packet);
            }
            packetsToSend.clear();
        }
    }

    private void handleUnknown(ByteBuffer buff, int type, int len) {
        Client.getLogger().finer("Unknown packet!");
        Client.getLogger().finer("0x" + Integer.toHexString(type) + "(" + type + ") | " + len);
        if (len > 100) {
            Client.getLogger().finer("Too big, won't capture");
        } else {
            Client.getLogger().finer("----------------------------");
            for (int i = buff.position(); i < buff.limit(); i++) {
                Client.getLogger().finer("" + (buff.get(i) & 0xFF));
            }
            Client.getLogger().finer("----------------------------");
        }
    }

    private void logReceivedPacket(Class<? extends com.bitbyterstudios.m3c.packets.receiving.ReceivingPacket> rPacket, int type, int len) {
        Client.getLogger().finest("Received packet, type " + rPacket.getSimpleName() + "(" + type + ")"
                + ", hex 0x" + Integer.toHexString(type) + " len " + len);
    }

    private boolean handleLogin() throws IOException {
        HandShake00 handShake = new HandShake00(socket.getInetAddress().getHostAddress(), socket.getPort());
        handShake.create();
        handShake.send(out, compressionThreshold);

        LoginStart00 loginStart = new LoginStart00(data.getUser());
        loginStart.create();
        loginStart.send(out, compressionThreshold);

        while (true) {
            ByteBuffer buff;
            int len;

            if (compressionThreshold > 0) {
                int plen = readVarInt(in); //packet length
                len = readVarInt(in); //length of uncompressed
                plen -= Utilities.getVarIntWidth(len);

                byte[] raw = new byte[plen];
                in.readFully(raw);

                if (len > 0) {
                    try {
                        buff = ByteBuffer.wrap(Utilities.decompress(raw));
                    } catch (DataFormatException e) {
                        e.printStackTrace();
                        continue;
                    }
                } else {
                    buff = ByteBuffer.wrap(raw);
                }
            } else {
                len = readVarInt(in);
                byte[] raw = new byte[len];
                in.readFully(raw);
                buff = ByteBuffer.wrap(raw);
            }

            int type = readVarInt(buff);


            ReceivingPacket handlingPacket = login.get(type);
            if (handlingPacket == null) {
                Client.getLogger().severe("Received unknown  packet, type 0x" + Integer.toHexString(type) + " with length " + len);
                throw new IllegalStateException("Couldn't find a packet to handle type " + type + "(len " + len + ")!");
            } else {
                Client.getLogger().finest("Received packet, type " + handlingPacket.getClass().getSimpleName() + ", len " + len);
            }
            handlingPacket.handle(buff, this);
            if (type == 0) { //We got kicked :(
                return false;
            } else if (type == 1) { //We should encrypt!
                encrypt((EncryptionRequest01) handlingPacket);
            } else if (type == 2) { //Yay, successful login!
                return true;
            }
        }
    }

    private void encrypt(EncryptionRequest01 req) throws IOException {
        SecretKey secretKey = CryptoHelper.createNewSharedKey();//generate a secret key
        String servId = CryptoHelper.getServerIdHash(req.getServerId(), req.getPubKey(), secretKey);
        ApiAccess.sendSessionRequest(data.getAccessToken(), servId, data.getUuid());
        byte[] secret = CryptoHelper.encryptData(req.getPubKey(), secretKey.getEncoded());
        byte[] verify = CryptoHelper.encryptData(req.getPubKey(), req.getVerifyToken());
        EncryptionResponse01 response = new EncryptionResponse01(secret, verify);
        response.create();
        response.send(out, compressionThreshold);
        out.flush();
        BufferedOutputStream buffOut = new BufferedOutputStream(CryptoHelper.encryptOuputStream(secretKey, socket.getOutputStream()));
        out = new DataOutputStream(buffOut);
        in = new DataInputStream(CryptoHelper.decryptInputStream(secretKey, socket.getInputStream()));
    }

    public int readVarInt(DataInputStream ins) throws IOException{
        int i = 0;
        int j = 0;
        while (true){
            int k = ins.read();

            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) throw new RuntimeException("VarInt too big");

            if ((k & 0x80) != 128) break; //MSB not set? 0x80 = 1000 0000(b)
        }

        return i;
    }

    public int readVarInt(ByteBuffer buff) {
        int i = 0;
        int j = 0;
        while (true) {
            int k = buff.get();

            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) throw new RuntimeException("VarInt too big");

            if ((k & 0x80) != 128) break; //MSB not set? 0x80 = 1000 0000(b)
        }

        return i;
    }

    public void addPacketToSend(SendingPacket packet) {
        packetsToSend.add(packet);
    }

    private void send(SendingPacket packet) throws IOException {
        ByteBuffer buffer = packet.getBuffer();
        ByteArrayDataOutput buff2 = ByteStreams.newDataOutput();

        if (compressionThreshold > 0) {
            if (buffer.array().length > compressionThreshold) {
                Utilities.writeVarInt(buff2, buffer.array().length);
                buff2.write(Utilities.compress(buffer.array()));
            } else {
                buff2.write(0);
                Utilities.writeVarInt(buff2, packet.getType());
                buff2.write(buffer.array());
            }
        } else {
            Utilities.writeVarInt(buff2, packet.getType());
            buff2.write(buffer.array());
        }

        ByteArrayDataOutput buffer3 = ByteStreams.newDataOutput();
        Utilities.writeVarInt(buffer3, buff2.toByteArray().length);
        buffer3.write(buff2.toByteArray());
        out.write(buffer3.toByteArray());
        out.flush();
    }

    private void loadPackets() {
        login = new HashMap<>();
        login.put(0, new Disconnect00());
        login.put(1, new EncryptionRequest01());
        login.put(2, new LoginSuccess02());
        login.put(0x03, new SetCompression03());

        packets = new HashMap<>();
        packets.put(0x00, KeepAlive00.class);
        packets.put(0x01, JoinGame01.class);
        packets.put(0x02, Chat02.class);
        packets.put(0x03, Time03.class);
        packets.put(0x04, EntityEquipment04.class);
        packets.put(0x05, SpawnPosition05.class);
        packets.put(0x06, Health06.class);
        packets.put(0x07, Respawn07.class);
        packets.put(0x08, PlayerPositionLook08.class);
        packets.put(0x09, HeldItemChange09.class);
        packets.put(0x0A, UseBed0A.class);
        packets.put(0x0B, Animation0B.class);
        packets.put(0x0C, SpawnPlayer0C.class);
        packets.put(0x0D, CollectItem0D.class);
        packets.put(0x0E, SpawnObject0E.class);
        packets.put(0x0F, SpawnMob0F.class);
        packets.put(0x10, SpawnPainting10.class);
        packets.put(0x11, SpawnExperienceOrbs11.class);
        packets.put(0x12, EntityVelocity12.class);
        packets.put(0x13, DestroyEntity13.class);
        packets.put(0x14, Entity14.class);
        packets.put(0x15, EntityRelativeMove15.class);
        packets.put(0x16, EntityLook16.class);
        packets.put(0x17, EntityLookAndRelativeMove17.class);
        packets.put(0x18, EntityTeleport18.class);
        packets.put(0x19, EntityHeadLook19.class);
        packets.put(0x1A, EntityStatus1A.class);
        packets.put(0x1B, AttachEntity1B.class);
        packets.put(0x1C, EntityMetadata1C.class);
        packets.put(0x1D, EntityEffect1D.class);
        packets.put(0x1E, RemoveEntityEffect1E.class);
        packets.put(0x1F, SetExperience1F.class);
        packets.put(0x20, EntityProperties20.class);
        packets.put(0x21, ChunkData21.class);
        packets.put(0x22, MultiBlockChange22.class);
        packets.put(0x23, BlockChange23.class);
        packets.put(0x24, BlockAction24.class);
        packets.put(0x25, BlockBreak25.class);
        packets.put(0x26, MapChunkBulk26.class);
        packets.put(0x27, Explosion27.class);
        packets.put(0x28, Effect28.class);
        packets.put(0x29, SoundEffect29.class);
        packets.put(0x2A, Particle2A.class);
        packets.put(0x2B, ChangeGamestate2B.class);
        packets.put(0x2C, SpawnGlobalEntity2C.class);
        packets.put(0x2D, OpenWindow2D.class);
        packets.put(0x2E, CloseWindow2E.class);
        packets.put(0x2F, SetSlot2F.class);
        packets.put(0x30, WindowItems30.class);
        packets.put(0x31, WindowProperty31.class);
        packets.put(0x32, ConfirmTransaction32.class);
        packets.put(0x33, UpdateSign33.class);
        packets.put(0x34, Map34.class);
        packets.put(0x35, UpdateBlockEntity35.class);
        packets.put(0x36, OpenSignEditor36.class);
        packets.put(0x37, Statistics37.class);
        packets.put(0x38, PlayerListItem38.class);
        packets.put(0x39, PlayerAbilities39.class);
        packets.put(0x3A, TabComplete3A.class);
        packets.put(0x3B, ScoreboardObjective3B.class);
        packets.put(0x3C, UpdateScore3C.class);
        packets.put(0x3D, DisplayScoreboard3D.class);
        packets.put(0x3E, Teams3E.class);
        packets.put(0x3F, PluginMessage3F.class);
        packets.put(0x40, Disconnect40.class);
        packets.put(0x41, ServerDifficulty41.class);
        packets.put(0x42, CombatEvent42.class);
        packets.put(0x43, Camera43.class);
        packets.put(0x44, WorldBorder44.class);
        packets.put(0x45, Title45.class);
        packets.put(0x46, SetCompression46.class);
        packets.put(0x47, PlayerListHeaderAndFooter47.class);
        packets.put(0x48, ResourcepackSend48.class);
        packets.put(0x49, UpdateEntityNbt49.class);
    }

    public Logger getLogger() {
        return Client.getLogger();
    }

    public Client getClient() {
        return client;
    }

    public ClientData getData() {
        return data;
    }

    public void setCompressionThreshold(int compressionThreshold) {
        this.compressionThreshold = compressionThreshold;
    }
}
