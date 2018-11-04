package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SpawnGlobalEntity2C extends ReceivingPacket {
    private int entityId;
    private byte type;
    private double x;
    private double y;
    private double z;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        type = buff.get();
        x = buff.getInt() / 32;
        y = buff.getInt() / 32;
        z = buff.getInt() / 32;
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
}
