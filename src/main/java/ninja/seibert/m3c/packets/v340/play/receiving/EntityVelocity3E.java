package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class EntityVelocity3E extends ReceivingPacket {
    private int entityId;
    private short velocityX;
    private short velocityY;
    private short velocityZ;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.velocityX = buff.getShort();
        this.velocityY = buff.getShort();
        this.velocityZ = buff.getShort();
    }

    public int getEntityId() {
        return entityId;
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
