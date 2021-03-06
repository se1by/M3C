package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class ConfirmTransaction32 extends ReceivingPacket {
    private byte windowId;
    private short actionNumber;
    private boolean accepted;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        windowId = (byte) (buff.get() & 0xFF);
        actionNumber = buff.getShort();
        accepted = buff.get() == 1;
    }

    public byte getWindowId() {
        return windowId;
    }

    public void setWindowId(byte windowId) {
        this.windowId = windowId;
    }

    public short getActionNumber() {
        return actionNumber;
    }

    public void setActionNumber(short actionNumber) {
        this.actionNumber = actionNumber;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
