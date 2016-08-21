
package com.bitbyterstudios.m3c;

import com.bitbyterstudios.m3c.packets.Protocol;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;
import com.bitbyterstudios.m3c.packets.SendingPacket;
import com.bitbyterstudios.m3c.packets.v47.login.receiving.EncryptionRequest01;
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
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

import javax.crypto.SecretKey;

public class ConnectionHandler {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Client client;
    private ClientData data;

    private Protocol protocol;
    private Collection<SendingPacket> packetsToSend;

    private int protocolVersion;
    private int compressionThreshold;

    public ConnectionHandler(Client client, ClientData data) {
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
            protocol = Protocol.getProtocol(protocolVersion);
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
            ReceivingPacket packet = protocol.getPlayPacket(type);
            if (packet == null) {
                if (unknown > 3) {
                    return;
                }
                logUnknownPacket(buff, type, uncompressedLength);
                unknown++;
                continue;
            }

            logReceivedPacket(packet, type, uncompressedLength);
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

    private void logReceivedPacket(ReceivingPacket rPacket, int type, int len) {
        Client.getLogger().finest("Received packet, type " + rPacket.getClass().getSimpleName() + "(" + type + ")"
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

            ReceivingPacket packet = protocol.getLoginPacket(type);
            if (packet == null) {
                logUnknownPacket(buff, type, length);
                //likely to fail beyond this point, but we try
                continue;
            }

            logReceivedPacket(packet, type, length);
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
