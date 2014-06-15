package com.bitbyterstudios.m3c.packets.sending;

public class HandShake00 extends SendingPacket {
    private static final int PACKET_ID = 0;
    private static final int PROTOCOL_VERSION = 5;

    private String host;
    private int port;

    public HandShake00(String host, int port) {
        super();
        this.host = host;
        this.port = port;
    }

    public void create() {
        buff.writeByte(PACKET_ID);
        buff.writeByte(PROTOCOL_VERSION);
        writeString(host);
        buff.writeShort(port);
        buff.writeByte(2);
    }
}
