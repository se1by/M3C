
package com.bitbyterstudios.m3c;

import com.bitbyterstudios.m3c.packets.receiving.*;
import com.bitbyterstudios.m3c.packets.sending.EncryptionResponse01;
import com.bitbyterstudios.m3c.packets.sending.HandShake00;
import com.bitbyterstudios.m3c.packets.sending.LoginStart00;
import com.bitbyterstudios.m3c.packets.sending.SendingPacket;
import com.bitbyterstudios.m3c.util.CryptoHelper;
import com.bitbyterstudios.m3c.util.Utilities;

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
    private HashMap<Integer, ReceivingPacket> packets;
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
            packetsToSend = new ArrayList<SendingPacket>();
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
                    //let's "fix" len for debug output below
                    len = plen - 1;
                }
            } else {
                len = readVarInt(in);
                byte[] raw = new byte[len];
                in.readFully(raw);
                buff = ByteBuffer.wrap(raw);
            }

            int type = readVarInt(buff);
            ReceivingPacket rPacket = packets.get(type);
            if (rPacket == null) {
                if (unknown > 3) {
                    return;
                }
                handleUnknown(buff, type, len);
                unknown++;
            } else {
                logReceivedPacket(rPacket, type, len);
                rPacket.handle(buff, this);
            }

            for (SendingPacket packet : packetsToSend) {
                Client.getLogger().finest("sending packet " + packet.getClass().getSimpleName());
                packet.send(out, compressionThreshold);
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

    private void logReceivedPacket(ReceivingPacket rPacket, int type, int len) {
        Client.getLogger().finest("Received packet, type " + rPacket.getClass().getSimpleName()
                + (rPacket.getClass().getSimpleName().equals(TrashPacket.class.getSimpleName()) ? "(" + type + ")" : "")
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

    private void loadPackets() {
        login = new HashMap<Integer, ReceivingPacket>();
        login.put(0, new Disconnect00());
        login.put(1, new EncryptionRequest01());
        login.put(2, new LoginSuccess02());
        login.put(0x03, new SetCompression03());


        TrashPacket trashPacket = new TrashPacket();
        packets = new HashMap<Integer, ReceivingPacket>();
        packets.put(0, new KeepAlive00());
        packets.put(1, new JoinGame01());
        packets.put(2, new Chat02());
        packets.put(3, new Time03());
        packets.put(4, trashPacket); //Entity Equipment
        packets.put(5, new SpawnPosition05());
        packets.put(6, new Health06());
        packets.put(7, new Respawn07());
        packets.put(8, new PlayerPositionLook08());
        packets.put(9, new HeldItemChange09());
        packets.put(11, trashPacket); //Animation
        packets.put(12, trashPacket); //Player Spawn (visible range)
        packets.put(13, trashPacket); //Collect Item
        packets.put(14, trashPacket); //Object(Vehicle) Spawn
        packets.put(15, trashPacket); //Entity Spawn (should add that later)
        packets.put(18, trashPacket); //Entity Velocity
        packets.put(19, trashPacket); //Entity Destroy
        packets.put(21, trashPacket); //Entity Move (relative, max 4 blocks)
        packets.put(22, trashPacket); //Entity Look
        packets.put(23, trashPacket); //Entity Look & Relative Move
        packets.put(24, trashPacket); //Entity Teleport (move > 4 blocks)
        packets.put(25, trashPacket); //Entity Head Look
        packets.put(26, new Status26());
        packets.put(27, trashPacket); //Attach Entity
        packets.put(28, trashPacket); //Entity Metadata
        packets.put(29, trashPacket); //Entity Effect
        packets.put(31, new Experience31());
        packets.put(32, trashPacket); //Entity Properties
        packets.put(33, trashPacket); //Multi Block Change
        packets.put(34, trashPacket); //Multi Block Change
        packets.put(35, trashPacket); //Block Change, not important for now
        packets.put(38, trashPacket); //Map bulk
        packets.put(40, trashPacket); //Effect
        packets.put(41, new SoundEffect41()); //Sound Effect
        packets.put(42, trashPacket); //Particles
        packets.put(43, trashPacket); //Gamestate Change (rain, credits)
        packets.put(44, trashPacket); //Entity Metadata
        packets.put(46, trashPacket); //Close window
        packets.put(47, trashPacket); //Inventory
        packets.put(48, trashPacket); //Inventory
        packets.put(51, trashPacket); //Display Scoreboard
        packets.put(53, trashPacket); //Block Entity Update
        packets.put(55, new Statistic55());
        packets.put(56, new PlayerListItem56());
        packets.put(57, new PlayerAbilities57());
        packets.put(59, trashPacket); //Scoreboards
        packets.put(60, trashPacket); //Scoreboards (update)
        packets.put(61, trashPacket); //Scoreboards
        packets.put(62, trashPacket); //Teams
        packets.put(63, new PluginMessage63());
        packets.put(64, new Disconnect00());
        packets.put(65, trashPacket); //Server difficultystop

        packets.put(68, trashPacket); //Worldborder
        packets.put(69, trashPacket); //Title
        packets.put(70, new SetCompression70());
        packets.put(71, trashPacket); //Player list header/footer
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
