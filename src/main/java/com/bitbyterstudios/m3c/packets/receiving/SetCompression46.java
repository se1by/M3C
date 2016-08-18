package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class SetCompression46 extends ReceivingPacket { // Legacy packet; to be removed in 1.9
    private int threshold;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        threshold = readVarInt(buff);
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
