package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class UpdateEntityNbt49 extends ReceivingPacket {
    private int entityId;
    private byte[] rawNbtTag;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = readVarInt(buff);
        rawNbtTag = new byte[buff.remaining()];
        for (int i = 0; buff.hasRemaining(); i++) {
            rawNbtTag[i] = buff.get();
        }
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public byte[] getRawNbtTag() {
        return rawNbtTag;
    }

    public void setRawNbtTag(byte[] rawNbtTag) {
        this.rawNbtTag = rawNbtTag;
    }
}
