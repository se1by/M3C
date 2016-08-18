
package com.bitbyterstudios.m3c;

import com.bitbyterstudios.m3c.packets.ReceivingPacket;
import com.bitbyterstudios.m3c.packets.SendingPacket;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Animation0B;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.AttachEntity1B;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.BlockAction24;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.BlockBreak25;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.BlockChange23;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Camera43;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.ChangeGamestate2B;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Chat02;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.ChunkData21;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.CloseWindow2E;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.CollectItem0D;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.CombatEvent42;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.ConfirmTransaction32;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.DestroyEntity13;
import com.bitbyterstudios.m3c.packets.v47.login.receiving.Disconnect00;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Disconnect40;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.DisplayScoreboard3D;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Effect28;
import com.bitbyterstudios.m3c.packets.v47.login.receiving.EncryptionRequest01;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Entity14;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityEffect1D;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityEquipment04;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityHeadLook19;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityLook16;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityLookAndRelativeMove17;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityMetadata1C;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityProperties20;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityRelativeMove15;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityStatus1A;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityTeleport18;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityVelocity12;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Explosion27;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Health06;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.HeldItemChange09;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.JoinGame01;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.KeepAlive00;
import com.bitbyterstudios.m3c.packets.v47.login.receiving.LoginSuccess02;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Map34;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.MapChunkBulk26;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.MultiBlockChange22;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.OpenSignEditor36;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.OpenWindow2D;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Particle2A;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.PlayerAbilities39;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.PlayerListHeaderAndFooter47;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.PlayerListItem38;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.PlayerPositionLook08;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.PluginMessage3F;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.RemoveEntityEffect1E;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.ResourcepackSend48;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Respawn07;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.ScoreboardObjective3B;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.ServerDifficulty41;
import com.bitbyterstudios.m3c.packets.v47.login.receiving.SetCompression03;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SetCompression46;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SetExperience1F;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SetSlot2F;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SoundEffect29;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnExperienceOrbs11;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnGlobalEntity2C;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnMob0F;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnObject0E;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnPainting10;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnPlayer0C;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnPosition05;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Statistics37;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.TabComplete3A;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Teams3E;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Time03;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Title45;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.UpdateBlockEntity35;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.UpdateEntityNbt49;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.UpdateScore3C;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.UpdateSign33;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.UseBed0A;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.WindowItems30;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.WindowProperty31;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.WorldBorder44;
import com.bitbyterstudios.m3c.packets.v47.login.sending.EncryptionResponse01;
import com.bitbyterstudios.m3c.packets.v47.login.sending.Handshake00;
import com.bitbyterstudios.m3c.packets.v47.login.sending.LoginStart00;
import com.bitbyterstudios.m3c.util.CryptoHelper;
import com.bitbyterstudios.m3c.util.Utilities;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

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

import javax.crypto.SecretKey;

public class ServerHandler {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Client client;
    private ClientData data;

    private HashMap<Integer, Class<? extends ReceivingPacket>> login;
    private HashMap<Integer, Class<? extends ReceivingPacket>> packets;
    private Collection<SendingPacket> packetsToSend;

    private int protocolVersion;
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
            //TODO: determine protocolVersion of server
            protocolVersion = 47;
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
            int uncompressedLength;

            if (compressionThreshold > 0) {
                int packetLength = Utilities.readVarInt(in); //packet length
                uncompressedLength = Utilities.readVarInt(in); //length of uncompressed
                packetLength -= Utilities.getVarIntWidth(uncompressedLength);

                byte[] rawPacket = new byte[packetLength];
                in.readFully(rawPacket);

                if (uncompressedLength > 0) {
                    try {
                        buff = ByteBuffer.wrap(Utilities.decompress(rawPacket));
                    } catch (DataFormatException e) {
                        e.printStackTrace();
                        continue;
                    }
                } else {
                    buff = ByteBuffer.wrap(rawPacket);
                    //set uncompressedLength for log output
                    uncompressedLength = packetLength - 1;
                }
            } else {
                uncompressedLength = Utilities.readVarInt(in);
                byte[] raw = new byte[uncompressedLength];
                in.readFully(raw);
                buff = ByteBuffer.wrap(raw);
            }

            int type = Utilities.readVarInt(buff);
            Class<? extends ReceivingPacket> packetClass = packets.get(type);
            if (packetClass == null) {
                if (unknown > 3) {
                    return;
                }
                logUnknownPacket(buff, type, uncompressedLength);
                unknown++;
                continue;
            }

            logReceivedPacket(packets.get(type), type, uncompressedLength);
            ReceivingPacket packet = null;
            try {
                packet = packetClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            packet.handle(buff, this);
            getClient().getPluginManager().callListeners(packet, this);

            for (SendingPacket sendingPacket : packetsToSend) {
                Client.getLogger().finest("sending packet " + sendingPacket.getClass().getSimpleName());
                send(sendingPacket);
            }
            packetsToSend.clear();
        }
    }

    private void logUnknownPacket(ByteBuffer buff, int type, int len) {
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

    private void logReceivedPacket(Class<? extends ReceivingPacket> rPacket, int type, int len) {
        Client.getLogger().finest("Received packet, type " + rPacket.getSimpleName() + "(" + type + ")"
                + ", hex 0x" + Integer.toHexString(type) + " len " + len);
    }

    private boolean handleLogin() throws IOException {
        send(new Handshake00(protocolVersion, socket.getInetAddress().getHostAddress(), (short) socket.getPort()));

        send(new LoginStart00(data.getUser()));

        while (true) {
            ByteBuffer buff;
            int length;

            if (compressionThreshold > 0) {
                int packetLength = Utilities.readVarInt(in); //packet length
                length = Utilities.readVarInt(in); //length of uncompressed
                packetLength -= Utilities.getVarIntWidth(length);

                byte[] raw = new byte[packetLength];
                in.readFully(raw);

                if (length > 0) {
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
                length = Utilities.readVarInt(in);
                byte[] raw = new byte[length];
                in.readFully(raw);
                buff = ByteBuffer.wrap(raw);
            }

            int type = Utilities.readVarInt(buff);

            Class<? extends ReceivingPacket> packetClass = login.get(type);
            if (packetClass == null) {
                logUnknownPacket(buff, type, length);
                //likely to fail beyond this point, but we try
                continue;
            }

            logReceivedPacket(packetClass, type, length);
            ReceivingPacket packet = null;
            try {
                packet = packetClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            packet.handle(buff, this);
            getClient().getPluginManager().callListeners(packet, this);

            if (type == 0) { //We got kicked :(
                return false;
            } else if (type == 1) { //We should encrypt!
                encrypt((EncryptionRequest01) packet);
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
        send(new EncryptionResponse01(secret, verify));
        BufferedOutputStream buffOut = new BufferedOutputStream(CryptoHelper.encryptOuputStream(secretKey, socket.getOutputStream()));
        out = new DataOutputStream(buffOut);
        in = new DataInputStream(CryptoHelper.decryptInputStream(secretKey, socket.getInputStream()));
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
        login.put(0, Disconnect00.class);
        login.put(1, EncryptionRequest01.class);
        login.put(2, LoginSuccess02.class);
        login.put(0x03, SetCompression03.class);

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
