package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class CloseWindow2E extends ReceivingPacket {
    private byte windowId;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        windowId = (byte) (buff.get() & 0xFF);
    }
}
