package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class WindowProperty31 extends ReceivingPacket {
    private byte windowId;
    private short property;
    private short value;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        windowId = (byte) (buff.get() & 0xFF);
        property = buff.getShort();
        value = buff.getShort();
    }

    public byte getWindowId() {
        return windowId;
    }

    public void setWindowId(byte windowId) {
        this.windowId = windowId;
    }

    public short getProperty() {
        return property;
    }

    public void setProperty(short property) {
        this.property = property;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }
}
