package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.Slot;

import java.nio.ByteBuffer;

public class EntityEquipment3F extends ReceivingPacket {
    private int entityId;
    private int slot;
    private Slot item;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.slot = readVarInt(buff);
        this.item = Slot.fromByteBuffer(buff);
    }

    public int getEntityId() {
        return entityId;
    }

    public int getSlot() {
        return slot;
    }

    public Slot getItem() {
        return item;
    }

}
