package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class HeldItemChange09 extends ReceivingPacket {
    private byte slot;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        slot = buff.get();
    }

    public byte getSlot() {
        return slot;
    }

    public void setSlot(byte slot) {
        this.slot = slot;
    }
}
