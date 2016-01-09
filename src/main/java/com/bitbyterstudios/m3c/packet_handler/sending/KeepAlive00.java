package com.bitbyterstudios.m3c.packet_handler.sending;

public class KeepAlive00 extends SendingPacket {
    private static final int PACKET_ID = 0;
    private int id;

    public KeepAlive00() {
        super();
    }

    @Override
    public void create() {
        writeVarInt(PACKET_ID);
        writeVarInt(id);
    }

    public void setId(int id) {
        this.id = id;
    }
}
