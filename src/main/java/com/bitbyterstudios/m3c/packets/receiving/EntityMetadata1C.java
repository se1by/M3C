package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class EntityMetadata1C extends ReceivingPacket {
    private int entityId;
    private byte[] rawMetadata;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = readVarInt(buff);
        rawMetadata = new byte[buff.remaining()];
        for (int i = 0; buff.hasRemaining(); i++) {
            rawMetadata[i] = buff.get();
        }
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public byte[] getRawMetadata() {
        return rawMetadata;
    }

    public void setRawMetadata(byte[] rawMetadata) {
        this.rawMetadata = rawMetadata;
    }
}
