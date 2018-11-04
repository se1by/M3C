package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SpawnExperienceOrbs11 extends ReceivingPacket {
    private int entityId;
    private double x;
    private double y;
    private double z;
    private short count;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        x = buff.getInt() / 32;
        y = buff.getInt() / 32;
        z = buff.getInt() / 32;
        count = buff.getShort();
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
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

    public short getCount() {
        return count;
    }

    public void setCount(short count) {
        this.count = count;
    }
}
