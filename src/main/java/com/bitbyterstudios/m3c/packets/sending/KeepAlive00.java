package com.bitbyterstudios.m3c.packets.sending;

import java.nio.ByteBuffer;

public class KeepAlive00 extends SendingPacket {
    private int id;

    public KeepAlive00() {
    }

    public KeepAlive00(int id) {
        this.id = id;
    }

    @Override
    public ByteBuffer getBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        writeVarInt(buffer, id);
        return buffer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
