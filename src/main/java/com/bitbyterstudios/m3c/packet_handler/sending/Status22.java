package com.bitbyterstudios.m3c.packet_handler.sending;

public class Status22 extends SendingPacket {
    private static final int PACKET_ID = 22;
    private byte action; //mostly 0 for respawn

    public Status22() {
        super();
    }

    @Override
    public void create() {
        writeVarInt(PACKET_ID);
        buff.writeByte(action);
    }

    public void setAction(byte action) {
        this.action = action;
    }
}
