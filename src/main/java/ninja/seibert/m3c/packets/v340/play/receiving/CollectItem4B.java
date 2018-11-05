package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class CollectItem4B extends ReceivingPacket {
    private int collectedEntityId;
    private int collectorEntityId;
    private int pickupItemCount;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.collectedEntityId = readVarInt(buff);
        this.collectorEntityId = readVarInt(buff);
        this.pickupItemCount = readVarInt(buff);
    }

    public int getCollectedEntityId() {
        return collectedEntityId;
    }

    public int getCollectorEntityId() {
        return collectorEntityId;
    }

    public int getPickupItemCount() {
        return pickupItemCount;
    }

}
