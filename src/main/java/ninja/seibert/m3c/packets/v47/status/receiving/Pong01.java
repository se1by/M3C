package ninja.seibert.m3c.packets.v47.status.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Pong01 extends ReceivingPacket {
    private long payload;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.payload = buff.getLong();
    }

    public long getPayload() {
        return payload;
    }
}
