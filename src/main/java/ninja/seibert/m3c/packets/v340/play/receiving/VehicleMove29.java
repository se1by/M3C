package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class VehicleMove29 extends ReceivingPacket {
    private double x;
    private double y;
    private double z;

    private float yaw;
    private float pitch;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        x = buff.getDouble();
        y = buff.getDouble();
        z = buff.getDouble();

        yaw = buff.getFloat();
        pitch = buff.getFloat();
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
}
