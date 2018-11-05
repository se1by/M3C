package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class ConfirmTransaction11 extends ReceivingPacket {
    private byte windowId;
    private short actionNumber;
    private boolean accepted;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.windowId = buff.get();
        this.actionNumber = buff.getShort();
        this.accepted = buff.get() == 1;
    }

    public byte getWindowId() {
        return windowId;
    }

    public short getActionNumber() {
        return actionNumber;
    }

    public boolean isAccepted() {
        return accepted;
    }

}
