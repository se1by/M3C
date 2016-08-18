package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;
import com.bitbyterstudios.m3c.util.Slot;

import java.nio.ByteBuffer;

public class WindowItems30 extends ReceivingPacket {
    private byte windowId;
    private Slot[] slots;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        windowId = (byte) (buff.get() & 0xFF);
        slots = new Slot[buff.getShort()];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = Slot.fromByteBuffer(buff);
        }
    }

    public byte getWindowId() {
        return windowId;
    }

    public void setWindowId(byte windowId) {
        this.windowId = windowId;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(Slot[] slots) {
        this.slots = slots;
    }
}
