package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.EntityMetadata;

import java.nio.ByteBuffer;
import java.util.UUID;

public class SpawnPlayer05 extends ReceivingPacket {
    private int entityId;
    private UUID playerUUID;
    private double playerX;
    private double playerY;
    private double playerZ;
    private byte yaw;
    private byte pitch;
    private EntityMetadata metadata;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.playerUUID = readUUID(buff);
        this.playerX = buff.getDouble();
        this.playerY = buff.getDouble();
        this.playerZ = buff.getDouble();
        this.yaw = buff.get();
        this.pitch = buff.get();
        this.metadata = EntityMetadata.fromByteBuffer(buff);
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public double getPlayerX() {
        return playerX;
    }

    public double getPlayerY() {
        return playerY;
    }

    public double getPlayerZ() {
        return playerZ;
    }

    public byte getYaw() {
        return yaw;
    }

    public byte getPitch() {
        return pitch;
    }

    public EntityMetadata getMetadata() {
        return metadata;
    }

}
