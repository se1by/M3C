package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SpawnObject0E extends ReceivingPacket {
    private int entityId;
    private byte type;
    private double x;
    private double y;
    private double z;
    private byte pitch;
    private byte yaw;
    private byte[] raw_data;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = readVarInt(buff);
        type = buff.get();
        x = buff.getInt() / 32;
        y = buff.getInt() / 32;
        z = buff.getInt() / 32;
        pitch = buff.get();
        yaw = buff.get();
        raw_data = new byte[buff.remaining()];
        for (int i = 0; buff.hasRemaining(); i++) {
            raw_data[i] = buff.get();
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

    public byte getPitch() {
        return pitch;
    }

    public void setPitch(byte pitch) {
        this.pitch = pitch;
    }

    public byte getYaw() {
        return yaw;
    }

    public void setYaw(byte yaw) {
        this.yaw = yaw;
    }

    public byte[] getRaw_data() {
        return raw_data;
    }

    public void setRaw_data(byte[] raw_data) {
        this.raw_data = raw_data;
    }
}
