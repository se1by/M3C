package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;
import com.bitbyterstudios.m3c.util.Slot;

import java.nio.ByteBuffer;

public class EntityEquipment04 extends ReceivingPacket {
    private int entityId;
    private short slot;
    private Slot item;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        slot = buff.getShort();
        item = Slot.fromByteBuffer(buff);
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public short getSlot() {
        return slot;
    }

    public void setSlot(short slot) {
        this.slot = slot;
    }

    public Slot getItem() {
        return item;
    }

    public void setItem(Slot item) {
        this.item = item;
    }
}
