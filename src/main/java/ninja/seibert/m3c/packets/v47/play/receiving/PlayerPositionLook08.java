package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class PlayerPositionLook08 extends ReceivingPacket {
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private byte flags;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        x = buff.getDouble();
        y = buff.getDouble();
        z = buff.getDouble();
        yaw = buff.getFloat();
        pitch = buff.getFloat();
        flags = buff.get();
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

    public byte getFlags() {
        return flags;
    }

    public void setFlags(byte flags) {
        this.flags = flags;
    }
}
