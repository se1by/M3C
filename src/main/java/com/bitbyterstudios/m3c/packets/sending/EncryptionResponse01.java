package com.bitbyterstudios.m3c.packets.sending;

import com.bitbyterstudios.m3c.Client;

public class EncryptionResponse01 extends SendingPacket {
    private static final int PACKET_ID = 1;
    private final byte[] secret;
    private final byte[] verifyToken;

    public EncryptionResponse01(byte[] secret, byte[] verifyToken) {
        super();
        this.secret = secret;
        this.verifyToken = verifyToken;
        Client.getLogger().fine("secret length is " + secret.length);
    }

    @Override
    public void create() {
        buff.writeByte(PACKET_ID);
        writeBytes(secret);
        writeBytes(verifyToken);
    }
}
