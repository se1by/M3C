package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class CloseWindow2E extends ReceivingPacket {
    private byte windowId;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        windowId = (byte) (buff.get() & 0xFF);
    }
}
