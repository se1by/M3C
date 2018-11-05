package ninja.seibert.m3c.packets.v340.login.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.definitions.login.receiving.SetCompression;

import java.nio.ByteBuffer;

public class SetCompression03 extends SetCompression {
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
