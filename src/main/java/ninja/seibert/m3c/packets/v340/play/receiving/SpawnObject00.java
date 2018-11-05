package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;
import java.util.UUID;

public class SpawnObject00 extends ReceivingPacket {
    private int entityId;
    private UUID objectUuid;
    private byte type;
    private double x;
    private double y;
    private double z;
    private byte pitch;
    private byte yaw;
    private int data;
    private short velocityX;
    private short velocityY;
    private short velocityZ;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.objectUuid = readUUID(buff);
        this.type = buff.get();
        this.x = buff.getDouble();
        this.y = buff.getDouble();
        this.z = buff.getDouble();
        this.pitch = buff.get();
        this.yaw = buff.get();
        this.data = buff.getInt();
        this.velocityX = buff.getShort();
        this.velocityY = buff.getShort();
        this.velocityZ = buff.getShort();
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getObjectUuid() {
        return objectUuid;
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

    public byte getPitch() {
        return pitch;
    }

    public byte getYaw() {
        return yaw;
    }

    public int getData() {
        return data;
    }

    public short getVelocityX() {
        return velocityX;
    }

    public short getVelocityY() {
        return velocityY;
    }

    public short getVelocityZ() {
        return velocityZ;
    }
}
