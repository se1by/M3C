package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SpawnExperienceOrbs01 extends ReceivingPacket {
    private int entityId;
    private double x;
    private double y;
    private double z;
    private short count;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.x = buff.getDouble();
        this.y = buff.getDouble();
        this.z = buff.getDouble();
        this.count = buff.getShort();
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

    public short getCount() {
        return count;
    }

}
