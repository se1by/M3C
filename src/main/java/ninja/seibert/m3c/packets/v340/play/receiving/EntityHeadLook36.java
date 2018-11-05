package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class EntityHeadLook36 extends ReceivingPacket {
    private int entityId;
    private byte yaw;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.yaw = buff.get();
    }

    public int getEntityId() {
        return entityId;
    }

    public byte getYaw() {
        return yaw;
    }

}
