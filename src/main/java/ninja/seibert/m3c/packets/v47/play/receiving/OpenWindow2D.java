package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class OpenWindow2D extends ReceivingPacket {
    private byte windowId;
    private String windowType;
    private String windowTitle; // json encoded chat
    private byte slots;
    private Integer entityId; // Integer so we can represent null state

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        windowId = (byte) (buff.get() & 0xFF);
        windowType = readString(buff);
        windowTitle = readString(buff);
        slots = (byte) (buff.get() & 0xFF);
        if (buff.hasRemaining()) {
            entityId = buff.getInt();
        }
    }

    public byte getWindowId() {
        return windowId;
    }

    public void setWindowId(byte windowId) {
        this.windowId = windowId;
    }

    public String getWindowType() {
        return windowType;
    }

    public void setWindowType(String windowType) {
        this.windowType = windowType;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public byte getSlots() {
        return slots;
    }

    public void setSlots(byte slots) {
        this.slots = slots;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
}
