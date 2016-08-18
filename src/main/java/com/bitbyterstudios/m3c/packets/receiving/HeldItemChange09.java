package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class HeldItemChange09 extends ReceivingPacket {
    private byte slot;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        slot = buff.get();
    }

    public byte getSlot() {
        return slot;
    }

    public void setSlot(byte slot) {
        this.slot = slot;
    }
}
