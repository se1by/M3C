package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class PlayerPositionLook2F extends ReceivingPacket {
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private byte flags;
    private int teleportId;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.x = buff.getDouble();
        this.y = buff.getDouble();
        this.z = buff.getDouble();
        this.yaw = buff.getFloat();
        this.pitch = buff.getFloat();
        this.flags = buff.get();
        this.teleportId = readVarInt(buff);
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

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public byte getFlags() {
        return flags;
    }

}
