package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.util.Slot;

import java.nio.ByteBuffer;

public class SetSlot2F extends ReceivingPacket {
    private byte windowId;
    private short slot;
    private Slot slotData;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        windowId = buff.get();
        slot = buff.getShort();
        slotData = Slot.fromByteBuffer(buff);
    }

    public byte getWindowId() {
        return windowId;
    }

    public void setWindowId(byte windowId) {
        this.windowId = windowId;
    }

    public short getSlot() {
        return slot;
    }

    public void setSlot(short slot) {
        this.slot = slot;
    }

    public Slot getSlotData() {
        return slotData;
    }

    public void setSlotData(Slot slotData) {
        this.slotData = slotData;
    }
}
