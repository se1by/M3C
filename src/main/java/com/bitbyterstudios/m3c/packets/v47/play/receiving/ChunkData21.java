package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class ChunkData21 extends ReceivingPacket {
    private int chunkX;
    private int chunkZ;
    private boolean continuous;
    private short bitmask;
    private byte[] rawChunkData;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        chunkX = buff.getInt();
        chunkZ = buff.getInt();
        continuous = buff.get() == 1;
        bitmask = (short) (buff.getShort() & 0xFFFF);
        int size = readVarInt(buff);
        rawChunkData = new byte[buff.remaining()];
        for (int i = 0; buff.hasRemaining(); i++) {
            rawChunkData[i] = buff.get();
        }
    }

    public int getChunkX() {
        return chunkX;
    }

    public void setChunkX(int chunkX) {
        this.chunkX = chunkX;
    }

    public int getChunkZ() {
        return chunkZ;
    }

    public void setChunkZ(int chunkZ) {
        this.chunkZ = chunkZ;
    }

    public boolean isContinuous() {
        return continuous;
    }

    public void setContinuous(boolean continuous) {
        this.continuous = continuous;
    }

    public short getBitmask() {
        return bitmask;
    }

    public void setBitmask(short bitmask) {
        this.bitmask = bitmask;
    }

    public byte[] getRawChunkData() {
        return rawChunkData;
    }

    public void setRawChunkData(byte[] rawChunkData) {
        this.rawChunkData = rawChunkData;
    }
}
