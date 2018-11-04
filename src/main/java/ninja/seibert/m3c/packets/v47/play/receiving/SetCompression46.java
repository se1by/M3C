package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.Client;
import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SetCompression46 extends ReceivingPacket { // Legacy packet; to be removed in 1.9
    private int threshold;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        threshold = readVarInt(buff);
        Client.getLogger().severe("FUCK, OLD COMPRESSION!");
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
