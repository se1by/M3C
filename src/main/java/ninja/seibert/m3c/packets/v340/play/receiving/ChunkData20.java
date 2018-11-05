package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class ChunkData20 extends ReceivingPacket {
    private int chunkX;
    private int chunkZ;
    private boolean continuous;
    private short bitmask;
    //TODO: Convert to proper structure
    private byte[] rawChunkData;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.chunkX = buff.getInt();
        this.chunkZ = buff.getInt();
        this.continuous = buff.get() == 1;
        this.bitmask = (short) (buff.getShort() & 0xFFFF);
        int size = readVarInt(buff);
        this.rawChunkData = new byte[buff.remaining()];
        for (int i = 0; buff.hasRemaining(); i++) {
            this.rawChunkData[i] = buff.get();
        }
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkZ() {
        return chunkZ;
    }

    public boolean isContinuous() {
        return continuous;
    }

    public short getBitmask() {
        return bitmask;
    }

    public byte[] getRawChunkData() {
        return rawChunkData;
    }

}
