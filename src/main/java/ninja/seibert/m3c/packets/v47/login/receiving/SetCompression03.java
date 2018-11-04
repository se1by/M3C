package ninja.seibert.m3c.packets.v47.login.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SetCompression03 extends ReceivingPacket {
    private int threshold;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        threshold = readVarInt(buff);
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
