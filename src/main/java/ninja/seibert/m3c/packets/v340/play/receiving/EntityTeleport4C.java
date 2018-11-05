package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class EntityTeleport4C extends ReceivingPacket {
    private int entityId;
    private double x;
    private double y;
    private double z;
    private byte yaw;
    private byte pitch;
    private boolean onGround;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.x = buff.getDouble();
        this.y = buff.getDouble();
        this.z = buff.getDouble();
        this.yaw = buff.get();
        this.pitch = buff.get();
        this.onGround = buff.get() == 1;
    }

    public int getEntityId() {
        return entityId;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public byte getYaw() {
        return yaw;
    }

    public byte getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

}
