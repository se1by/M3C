package com.bitbyterstudios.m3c.packets.sending;

import com.bitbyterstudios.m3c.util.Utilities;

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
        ByteBuffer buffer = ByteBuffer.allocate(Utilities.getVarIntWidth(id));
        writeVarInt(buffer, id);
        return buffer;
    }

    @Override
    public int getType() {
        return 0x00;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
