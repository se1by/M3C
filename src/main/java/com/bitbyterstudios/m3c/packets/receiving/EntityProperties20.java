package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class EntityProperties20 extends ReceivingPacket {
    private int entityId;
    private byte[] raw_properties;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = readVarInt(buff);
        raw_properties = new byte[buff.remaining()];
        for (int i = 0; buff.hasRemaining(); i++) {
            raw_properties[i] = buff.get();
        }
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public byte[] getRaw_properties() {
        return raw_properties;
    }

    public void setRaw_properties(byte[] raw_properties) {
        this.raw_properties = raw_properties;
    }
}
