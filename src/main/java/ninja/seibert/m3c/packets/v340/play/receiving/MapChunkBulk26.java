package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class MapChunkBulk26 extends ReceivingPacket {
    private boolean skylight;
    private byte[] rawChunkData;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
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
