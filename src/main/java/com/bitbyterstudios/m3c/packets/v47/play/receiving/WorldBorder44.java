package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class WorldBorder44 extends ReceivingPacket {
    private int action;
    private double radius;
    private double oldRadius;
    private double newRadius;
    private long speed;
    private double x;
    private double z;
    private int portalTeleportBoundary;
    private int warningTime;
    private int warningBlocks;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        action = readVarInt(buff);
        switch (action) {
            case 0: // set size
                radius = readVarInt(buff);
                break;
            case 1: // lerp size
                oldRadius = buff.getDouble();
                newRadius = buff.getDouble();
                speed = readVarLong(buff);
                break;
            case 2: // set center
                x = buff.getDouble();
                z = buff.getDouble();
                break;
            case 3: // initialize
                x = buff.getDouble();
                z = buff.getDouble();
                oldRadius = buff.getDouble();
                newRadius = buff.getDouble();
                speed = readVarLong(buff);
                portalTeleportBoundary = readVarInt(buff);
                warningTime = readVarInt(buff);
                warningBlocks = readVarInt(buff);
                break;
            case 4: // set warning time
                warningTime = readVarInt(buff);
                break;
            case 5: // set warning blocks
                warningBlocks = readVarInt(buff);
                break;
            default:
                throw new IllegalStateException("Unkown action " + action + "!");
        }
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getOldRadius() {
        return oldRadius;
    }

    public void setOldRadius(double oldRadius) {
        this.oldRadius = oldRadius;
    }

    public double getNewRadius() {
        return newRadius;
    }

    public void setNewRadius(double newRadius) {
        this.newRadius = newRadius;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public int getPortalTeleportBoundary() {
        return portalTeleportBoundary;
    }

    public void setPortalTeleportBoundary(int portalTeleportBoundary) {
        this.portalTeleportBoundary = portalTeleportBoundary;
    }

    public int getWarningTime() {
        return warningTime;
    }

    public void setWarningTime(int warningTime) {
        this.warningTime = warningTime;
    }

    public int getWarningBlocks() {
        return warningBlocks;
    }

    public void setWarningBlocks(int warningBlocks) {
        this.warningBlocks = warningBlocks;
    }
}
