package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class KeepAlive00 extends ReceivingPacket {
    private int id;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        id = readVarInt(buff);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
