package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class EntityVelocity12 extends ReceivingPacket {
    private int entityId;
    private short velocityX;
    private short velocityY;
    private short velocityZ;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        velocityX = buff.getShort();
        velocityY = buff.getShort();
        velocityZ = buff.getShort();
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public short getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(short velocityX) {
        this.velocityX = velocityX;
    }

    public short getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(short velocityY) {
        this.velocityY = velocityY;
    }

    public short getVelocityZ() {
        return velocityZ;
    }

    public void setVelocityZ(short velocityZ) {
        this.velocityZ = velocityZ;
    }
}
