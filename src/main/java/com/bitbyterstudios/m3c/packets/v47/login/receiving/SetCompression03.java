package com.bitbyterstudios.m3c.packets.v47.login.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SetCompression03 extends ReceivingPacket {
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
