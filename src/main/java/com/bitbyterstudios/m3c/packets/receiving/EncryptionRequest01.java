package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.CryptoHelper;
import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.security.PublicKey;

public class EncryptionRequest01 extends ReceivingPacket {
    private String serverId;
    private PublicKey pubKey;
    private byte[] verifyToken;

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            serverId = readString(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] pubKeyBytes = bytesFromStream(in);
        pubKey = CryptoHelper.decodePublicKey(pubKeyBytes);
        verifyToken = bytesFromStream(in);
    }

    public String getServerId() {
        return serverId;
    }

    public PublicKey getPubKey() {
        return pubKey;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }
}
