package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.EntityMetadata;

import java.nio.ByteBuffer;
import java.util.UUID;

public class SpawnMob03 extends ReceivingPacket {
    private int entityId;
    private UUID entityUuid;
    private int type;
    private double x;
    private double y;
    private double z;
    private byte yaw;
    private byte pitch;
    private byte headPitch;
    private short velocityX;
    private short velocityY;
    private short velocityZ;
    private EntityMetadata metadata;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.entityUuid = readUUID(buff);
        this.type = readVarInt(buff);
        this.x = buff.getDouble();
        this.y = buff.getDouble();
        this.z = buff.getDouble();
        this.yaw = buff.get();
        this.pitch = buff.get();
        this.headPitch = buff.get();
        this.velocityX = buff.getShort();
        this.velocityY = buff.getShort();
        this.velocityZ = buff.getShort();
        this.metadata = EntityMetadata.fromByteBuffer(buff);
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getEntityUuid() {
        return entityUuid;
    }

    public int getType() {
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

    public byte getYaw() {
        return yaw;
    }

    public byte getPitch() {
        return pitch;
    }

    public byte getHeadPitch() {
        return headPitch;
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

    public EntityMetadata getMetadata() {
        return metadata;
    }

}
