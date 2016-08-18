package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class RemoveEntityEffect1E extends ReceivingPacket {
    private int entityId;
    private byte effectId;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = readVarInt(buff);
        effectId = buff.get();
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public byte getEffectId() {
        return effectId;
    }

    public void setEffectId(byte effectId) {
        this.effectId = effectId;
    }
}
