package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class EntityVelocity12 extends ReceivingPacket {
    private int entityId;
    private short velocityX;
    private short velocityY;
    private short velocityZ;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = readVarInt(buff);
        velocityX = buff.getShort();
        velocityY = buff.getShort();
        velocityZ = buff.getShort();
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public short getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(short velocityX) {
        this.velocityX = velocityX;
    }

    public short getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(short velocityY) {
        this.velocityY = velocityY;
    }

    public short getVelocityZ() {
        return velocityZ;
    }

    public void setVelocityZ(short velocityZ) {
        this.velocityZ = velocityZ;
    }
}
