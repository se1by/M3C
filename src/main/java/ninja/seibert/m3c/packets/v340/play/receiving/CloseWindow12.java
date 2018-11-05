package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class CloseWindow12 extends ReceivingPacket {
    private short windowId;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        windowId = (short) (buff.get() & 0xFF);
    }

    public short getWindowId() {
        return windowId;
    }
}
