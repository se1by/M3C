package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class WindowProperty15 extends ReceivingPacket {
    private short windowId;
    private short property;
    private short value;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        windowId = (short) (buff.get() & 0xFF);
        property = buff.getShort();
        value = buff.getShort();
    }

    public short getWindowId() {
        return windowId;
    }

    public short getProperty() {
        return property;
    }

    public short getValue() {
        return value;
    }

}
