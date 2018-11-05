package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class OpenWindow13 extends ReceivingPacket {
    private short windowId;
    private String windowType;
    private String windowTitle; // json encoded chat
    private short slots;
    private Integer entityId; // Integer so we can represent null state

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.windowId = (short) (buff.get() & 0xFF);
        this.windowType = readString(buff);
        this.windowTitle = readString(buff);
        this.slots = (short) (buff.get() & 0xFF);
        if (buff.hasRemaining()) {
            this.entityId = buff.getInt();
        }
    }

    public short getWindowId() {
        return windowId;
    }

    public String getWindowType() {
        return windowType;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public short getSlots() {
        return slots;
    }

    public Integer getEntityId() {
        return entityId;
    }

}
