package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class MapChunkBulk26 extends ReceivingPacket {
    private boolean skylight;
    private byte[] rawChunkData;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        skylight = buff.get() == 1;
        rawChunkData = new byte[buff.remaining()];

        for (int i = 0; buff.hasRemaining(); i++) {
            rawChunkData[i] = buff.get();
        }
    }

    public boolean isSkylight() {
        return skylight;
    }

    public void setSkylight(boolean skylight) {
        this.skylight = skylight;
    }

    public byte[] getRawChunkData() {
        return rawChunkData;
    }

    public void setRawChunkData(byte[] rawChunkData) {
        this.rawChunkData = rawChunkData;
    }
}
