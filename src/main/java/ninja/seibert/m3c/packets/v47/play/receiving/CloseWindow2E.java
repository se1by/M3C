package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class CloseWindow2E extends ReceivingPacket {
    private byte windowId;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        windowId = (byte) (buff.get() & 0xFF);
    }
}
