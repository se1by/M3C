package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class WorldBorder38 extends ReceivingPacket {
    private int action;
    private double diameter;
    private double oldDiameter;
    private double newDiameter;
    private long speed;
    private double x;
    private double z;
    private int portalTeleportBoundary;
    private int warningTime;
    private int warningBlocks;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.action = readVarInt(buff);
        switch (this.action) {
            case 0: // set size
                this.diameter = buff.getDouble();
                break;
            case 1: // lerp size
                this.oldDiameter = buff.getDouble();
                this.newDiameter = buff.getDouble();
                this.speed = readVarLong(buff);
                break;
            case 2: // set center
                this.x = buff.getDouble();
                this.z = buff.getDouble();
                break;
            case 3: // initialize
                this.x = buff.getDouble();
                this.z = buff.getDouble();
                this.oldDiameter = buff.getDouble();
                this.newDiameter = buff.getDouble();
                this.speed = readVarLong(buff);
                this.portalTeleportBoundary = readVarInt(buff);
                this.warningTime = readVarInt(buff);
                this.warningBlocks = readVarInt(buff);
                break;
            case 4: // set warning time
                this.warningTime = readVarInt(buff);
                break;
            case 5: // set warning blocks
                this.warningBlocks = readVarInt(buff);
                break;
            default:
                throw new IllegalStateException("Unkown action " + this.action + "!");
        }
    }

    public int getAction() {
        return action;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getOldDiameter() {
        return oldDiameter;
    }

    public double getNewDiameter() {
        return newDiameter;
    }

    public long getSpeed() {
        return speed;
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

    public int getPortalTeleportBoundary() {
        return portalTeleportBoundary;
    }

    public int getWarningTime() {
        return warningTime;
    }

    public int getWarningBlocks() {
        return warningBlocks;
    }

}
