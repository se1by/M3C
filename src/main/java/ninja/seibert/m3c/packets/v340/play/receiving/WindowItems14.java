package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.Slot;

import java.nio.ByteBuffer;

public class WindowItems14 extends ReceivingPacket {
    private short windowId;
    private Slot[] slots;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        windowId = (short) (buff.get() & 0xFF);
        slots = new Slot[buff.getShort()];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = Slot.fromByteBuffer(buff);
        }
    }

    public short getWindowId() {
        return windowId;
    }

    public Slot[] getSlots() {
        return slots;
    }

}
