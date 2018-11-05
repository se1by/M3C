package ninja.seibert.m3c.packets.v340.play.sending;

import ninja.seibert.m3c.packets.SendingPacket;

import java.nio.ByteBuffer;

public class KeepAlive0B extends SendingPacket {
    private long id;

    public KeepAlive0B() {
    }

    public KeepAlive0B(long id) {
        this.id = id;
    }

    @Override
    public ByteBuffer getBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(id);
        return buffer;
    }

    @Override
    public int getType() {
        return 0x0B;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
