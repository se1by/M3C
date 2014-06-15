package com.bitbyterstudios.m3c.packets.sending;

public class LoginStart00 extends SendingPacket {
    private static final int PACKET_ID = 0;
    private String user;

    public LoginStart00(String user) {
        super();
        this.user = user;
    }

    @Override
    public void create() {
        writeVarInt(PACKET_ID);
        writeString(user);
    }
}
