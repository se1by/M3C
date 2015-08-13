package com.bitbyterstudios.m3c.packets.sending;

import com.bitbyterstudios.m3c.util.Utilities;

public class HandShake00 extends SendingPacket {
    private static final int PACKET_ID = 0;
    private static final int PROTOCOL_VERSION = 47;

    private String host;
    private int port;

    public HandShake00(String host, int port) {
        super();
        this.host = host;
        this.port = port;
    }

    public void create() {
        Utilities.writeVarInt(buff, PACKET_ID);
        Utilities.writeVarInt(buff, PROTOCOL_VERSION);
        writeString(host);
        buff.writeShort(port);
        Utilities.writeVarInt(buff, 2); //Next state (2=Login, 1=Status)
    } // 1000 0000
}
