package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class ChangeGamestate2B extends ReceivingPacket {
    private byte reason;
    private float value; // best name ever

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        reason = (byte) (buff.get() & 0xFF);
        value = buff.getFloat();
    }

    public byte getReason() {
        return reason;
    }

    public void setReason(byte reason) {
        this.reason = reason;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
