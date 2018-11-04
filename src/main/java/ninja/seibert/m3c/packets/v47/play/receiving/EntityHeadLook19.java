package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class EntityHeadLook19 extends ReceivingPacket {
    private int entityId;
    private byte yaw;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        yaw = buff.get();
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public byte getYaw() {
        return yaw;
    }

    public void setYaw(byte yaw) {
        this.yaw = yaw;
    }
}
