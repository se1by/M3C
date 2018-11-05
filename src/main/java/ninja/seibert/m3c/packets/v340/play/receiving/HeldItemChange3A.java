package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class HeldItemChange3A extends ReceivingPacket {
    private byte slot;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.slot = buff.get();
    }

    public byte getSlot() {
        return slot;
    }
}
