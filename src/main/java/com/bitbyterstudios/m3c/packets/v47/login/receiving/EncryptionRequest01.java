package com.bitbyterstudios.m3c.packets.v47.login.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;
import com.bitbyterstudios.m3c.util.CryptoHelper;

import java.nio.ByteBuffer;
import java.security.PublicKey;

public class EncryptionRequest01 extends ReceivingPacket {
    private String serverId;
    private PublicKey pubKey;
    private byte[] verifyToken;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        serverId = readString(buff);
        byte[] pubKeyBytes = bytesFromBuff(buff);
        pubKey = CryptoHelper.decodePublicKey(pubKeyBytes);
        verifyToken = bytesFromBuff(buff);
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
