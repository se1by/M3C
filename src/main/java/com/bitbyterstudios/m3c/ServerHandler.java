package com.bitbyterstudios.m3c;

import com.bitbyterstudios.m3c.packets.receiving.*;
import com.bitbyterstudios.m3c.packets.sending.EncryptionResponse01;
import com.bitbyterstudios.m3c.packets.sending.HandShake00;
import com.bitbyterstudios.m3c.packets.sending.LoginStart00;
import com.bitbyterstudios.m3c.packets.sending.SendingPacket;

import javax.crypto.SecretKey;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

public class ServerHandler {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Client client;
    private ClientData data;

    private HashMap<Integer, ReceivingPacket> login;
    private HashMap<Integer, ReceivingPacket> packets;
    private Collection<SendingPacket> packetsToSend;

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
        HandShake00 handShake = new HandShake00(socket.getInetAddress().getHostAddress(), socket.getPort());
        handShake.create();
        handShake.send(out);

        LoginStart00 loginStart = new LoginStart00(data.getUser());
        loginStart.create();
        loginStart.send(out);

        while (true) {
            int len = readVarInt(in);
            int type = readVarInt(in);


            ReceivingPacket handlingPacket = login.get(type);
            if (handlingPacket == null) {
                Client.getLogger().severe("Received unknown  packet, type " + type + " with length " + len);
                throw new IllegalStateException("Couldn't find a packet to handle type " + type + "(len " + len + "!");
            } else {
                Client.getLogger().finest("Received packet, type " + handlingPacket.getClass().getSimpleName() + ", len " + len);
            }
            handlingPacket.read(in, len - 1, this);
            if (type == 0) { //We got kicked :(
                return;
            } else if (type == 1) { //We should encrypt!
                encrypt((EncryptionRequest01) handlingPacket);
            } else if (type == 2) { //Yay, successful login!
                //in.skipBytes(len -1);
                break;
            }
        }

        int unknown = 0;
        while (true) {
            int len = readVarInt(in);
            int type = readVarInt(in);

            ReceivingPacket handlingPacket = packets.get(type);
            if (handlingPacket == null) {
                if (unknown > 3) {
                    return;
                }
                Client.getLogger().finer(type + "|" + len);
                byte[] rawData = new byte[len - 1];
                in.readFully(rawData);
                Client.getLogger().finer("Unknown packet, type " + type + ", len " + len + ":");
                if (len > 100) {
                    Client.getLogger().finer("Too big to capture that :o");
                    unknown++;
                    continue;
                }
                Client.getLogger().finer("-------------------");
                for (byte b : rawData) {
                    Client.getLogger().finer("" + (b & 0xFF));
                }
                Client.getLogger().finer("-------------------");
                unknown++;
                continue;
                //throw new IllegalStateException("Couldn't find a packet to handle type " + type + "!");
            } else {
                Client.getLogger().finest("Received packet, type " + handlingPacket.getClass().getSimpleName()
                        + (handlingPacket.getClass().getSimpleName().equals(TrashPacket.class.getSimpleName()) ? "(" + type + ")" : "")
                        + ", len " + len);
            }
            handlingPacket.read(in, len - 1, this);
            for (SendingPacket packet : packetsToSend) {
                Client.getLogger().finest("sending packet " + packet.getClass().getSimpleName());
                packet.send(out);
            }
            packetsToSend.clear();
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
        response.send(out);
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

            if ((k & 0x80) != 128) break;
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


        TrashPacket trashPacket = new TrashPacket();
        packets = new HashMap<Integer, ReceivingPacket>();
        packets.put(0, new KeepAlive00());
        packets.put(1, new JoinGame01());
        packets.put(2, new Chat02());
        packets.put(3, new Time03());
        packets.put(4, trashPacket); //Entity Equipment
        packets.put(5, new SpawnPosition05());
        packets.put(6, new Health06());
        packets.put(8, new PlayerPositionLook08());
        packets.put(9, new HeldItemChange09());
        packets.put(12, trashPacket); //Player Spawn (visible range)
        packets.put(14, trashPacket); //Object(Vehicle) Spawn
        packets.put(15, trashPacket); //Entity Spawn (should add that later)
        packets.put(18, trashPacket); //Entity Velocity
        packets.put(19, trashPacket); //Entity Destroy
        packets.put(21, trashPacket); //Entity Move (relative, max 4 blocks)
        packets.put(22, trashPacket); //Entity Look
        packets.put(23, trashPacket); //Entity Look & Relative Move
        packets.put(24, trashPacket); //Entity Teleport (move > 4 blocks)
        packets.put(25, trashPacket); //Entity Head Look
        packets.put(26, trashPacket); //Entity Status (we need that later)
        packets.put(28, trashPacket); //Entity Metadata
        packets.put(31, new Experience31());
        packets.put(32, trashPacket); //Entity Properties
        packets.put(34, trashPacket); //Multi Block Change
        packets.put(35, trashPacket); //Block Change, not important for now
        packets.put(38, trashPacket); //Map bulk
        packets.put(41, trashPacket); //Sound Effect
        packets.put(43, trashPacket); //Gamestate Change (rain, credits)
        packets.put(47, trashPacket); //Inventory
        packets.put(48, trashPacket); //Inventory
        packets.put(53, trashPacket); //Block Entity Update
        packets.put(55, new Statistic55());
        packets.put(56, new PlayerListItem56());
        packets.put(57, new PlayerAbilities57());
        packets.put(63, new PluginMessage63());
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
}
