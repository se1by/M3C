package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Camera43 extends ReceivingPacket {
    private int cameraId;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        cameraId = readVarInt(buff);
    }
}
