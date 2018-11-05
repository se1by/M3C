package ninja.seibert.m3c.packets.v340.status.sending;

import ninja.seibert.m3c.packets.SendingPacket;

import java.nio.ByteBuffer;

public class Ping01 extends SendingPacket {
    private long payload = System.currentTimeMillis();

    @Override
    public ByteBuffer getBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putLong(payload); // Just a (pseudo)random payload
        return buffer;
    }

    @Override
    public int getType() {
        return 0x01;
    }

    public long getPayload() {
        return payload;
    }
}
