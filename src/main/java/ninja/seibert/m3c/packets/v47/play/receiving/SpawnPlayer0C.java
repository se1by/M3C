package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.EntityMetadata;

import java.nio.ByteBuffer;
import java.util.UUID;

public class SpawnPlayer0C extends ReceivingPacket {
    private int entityId;
    private UUID playerUUID;
    private double playerX;
    private double playerY;
    private double playerZ;
    private byte yaw;
    private byte pitch;
    private short currentItem;
    private EntityMetadata metadata;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        playerUUID = readUUID(buff);
        playerX = buff.getInt() / 32; //Needs testing
        playerY = buff.getInt() / 32;
        playerZ = buff.getInt() / 32;
        yaw = buff.get();
        pitch = buff.get();
        currentItem = buff.getShort();
        metadata = EntityMetadata.fromByteBuffer(buff);
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public double getPlayerX() {
        return playerX;
    }

    public void setPlayerX(double playerX) {
        this.playerX = playerX;
    }

    public double getPlayerY() {
        return playerY;
    }

    public void setPlayerY(double playerY) {
        this.playerY = playerY;
    }

    public double getPlayerZ() {
        return playerZ;
    }

    public void setPlayerZ(double playerZ) {
        this.playerZ = playerZ;
    }

    public byte getYaw() {
        return yaw;
    }

    public void setYaw(byte yaw) {
        this.yaw = yaw;
    }

    public byte getPitch() {
        return pitch;
    }

    public void setPitch(byte pitch) {
        this.pitch = pitch;
    }

    public short getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(short currentItem) {
        this.currentItem = currentItem;
    }

    public EntityMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(EntityMetadata metadata) {
        this.metadata = metadata;
    }
}
