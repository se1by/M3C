package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class EntityHeadLook19 extends ReceivingPacket {
    private int entityId;
    private byte yaw;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = readVarInt(buff);
        yaw = buff.get();
        Client.getLogger().finest("EntityID " + entityId + ", new angle: " + (yaw * 360/255));
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public byte getYaw() {
        return yaw;
    }

    public void setYaw(byte yaw) {
        this.yaw = yaw;
    }
}
