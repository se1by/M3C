package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SpawnGlobalEntity02 extends ReceivingPacket {
    private int entityId;
    private byte type;
    private double x;
    private double y;
    private double z;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.type = buff.get();
        this.x = buff.getDouble();
        this.y = buff.getDouble();
        this.z = buff.getDouble();
    }

    public int getEntityId() {
        return entityId;
    }

    public byte getType() {
        return type;
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

}
