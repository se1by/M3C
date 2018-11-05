package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.Slot;

import java.nio.ByteBuffer;

public class SetSlot16 extends ReceivingPacket {
    private byte windowId;
    private short slot;
    private Slot slotData;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        windowId = buff.get();
        slot = buff.getShort();
        slotData = Slot.fromByteBuffer(buff);
    }

    public byte getWindowId() {
        return windowId;
    }

    public short getSlot() {
        return slot;
    }

    public Slot getSlotData() {
        return slotData;
    }

}
