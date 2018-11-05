package ninja.seibert.m3c.packets.v340.status.sending;

import ninja.seibert.m3c.packets.SendingPacket;

import java.nio.ByteBuffer;

public class Request00 extends SendingPacket {
    @Override
    public ByteBuffer getBuffer() {
        return ByteBuffer.allocate(0);
    }

    @Override
    public int getType() {
        return 0x00;
    }
}
