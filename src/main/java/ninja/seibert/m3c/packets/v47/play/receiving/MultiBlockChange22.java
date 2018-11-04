package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class MultiBlockChange22 extends ReceivingPacket {
    private int chunkX;
    private int chunkY;
    private byte[] rawMultiBlockChange;
    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        chunkX = buff.getInt();
        chunkY = buff.getInt();
        int size = readVarInt(buff);
        rawMultiBlockChange = new byte[buff.remaining()];
        for (int i = 0; buff.hasRemaining(); i++) {
            rawMultiBlockChange[i] = buff.get();
        }
    }

    public int getChunkX() {
        return chunkX;
    }

    public void setChunkX(int chunkX) {
        this.chunkX = chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }

    public void setChunkY(int chunkY) {
        this.chunkY = chunkY;
    }

    public byte[] getRawMultiBlockChange() {
        return rawMultiBlockChange;
    }

    public void setRawMultiBlockChange(byte[] rawMultiBlockChange) {
        this.rawMultiBlockChange = rawMultiBlockChange;
    }
}
