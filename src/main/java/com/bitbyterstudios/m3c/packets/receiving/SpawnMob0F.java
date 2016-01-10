package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class SpawnMob0F extends ReceivingPacket {
    private int entityId;
    private byte type;
    private double x;
    private double y;
    private double z;
    private byte yaw;
    private byte pitch;
    private short velocityX;
    private short velocityY;
    private short velocityZ;
    private byte[] raw_metadata;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = readVarInt(buff);
        type = (byte) (buff.get() & 0xFF);
        x = buff.getInt() / 32;
        y = buff.getInt() / 32;
        z = buff.getInt() / 32;
        yaw = buff.get();
        pitch = buff.get();
        velocityX = buff.getShort();
        velocityY = buff.getShort();
        velocityZ = buff.getShort();
        raw_metadata = new byte[buff.remaining()];
        for (int i = 0; buff.hasRemaining(); i++) {
            raw_metadata[i] = buff.get();
        }
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public byte getYaw() {
        return yaw;
    }

    public void setYaw(byte yaw) {
        this.yaw = yaw;
    }

    public byte getPitch() {
        return pitch;
    }

    public void setPitch(byte pitch) {
        this.pitch = pitch;
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

    public byte[] getRaw_metadata() {
        return raw_metadata;
    }

    public void setRaw_metadata(byte[] raw_metadata) {
        this.raw_metadata = raw_metadata;
    }
}
