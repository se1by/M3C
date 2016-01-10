package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Entity14 extends ReceivingPacket {
    private int entityId;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = readVarInt(buff);
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
}
