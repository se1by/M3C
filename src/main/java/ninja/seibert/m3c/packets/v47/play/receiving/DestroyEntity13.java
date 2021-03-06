package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class DestroyEntity13 extends ReceivingPacket {
    private int[] entityIds;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        int size = readVarInt(buff);
        entityIds = new int[size];
        for (int i = 0; i < size; i++) {
            entityIds[i] = readVarInt(buff);
        }
    }

    public int[] getEntityIds() {
        return entityIds;
    }

    public void setEntityIds(int[] entityIds) {
        this.entityIds = entityIds;
    }
}
