package com.bitbyterstudios.m3c.util;

import com.bitbyterstudios.m3c.packet_handler.receiving.PlayerPositionLook08;
import com.bitbyterstudios.m3c.packet_handler.sending.PlayerPositionLook06;

public class Location {
    private double x;
    private double y;
    private double z;

    private float yaw;
    private float pitch;
    private boolean onGround;

    public Location(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public Location() {
    }

    public PlayerPositionLook06 getPlayerPositionLookPacket() {
        PlayerPositionLook06 packet = new PlayerPositionLook06();
        packet.setPosition(x, y, z, yaw, pitch, onGround);
        packet.create();
        return packet;
    }

    public void updateFromPlayerPositionLook08(PlayerPositionLook08 packet) {
        setX(packet.getX());
        setY(packet.getY());
        setZ(packet.getZ());
        setYaw(packet.getYaw());
        setPitch(packet.getPitch());
        setOnGround(packet.isOnGround());
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

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
