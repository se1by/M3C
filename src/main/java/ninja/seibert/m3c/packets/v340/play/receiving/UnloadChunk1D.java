package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class UnloadChunk1D extends ReceivingPacket {
    private int chunkX;
    private int chunkY;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.chunkX = readVarInt(buff);
        this.chunkY = readVarInt(buff);
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }
}
