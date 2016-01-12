package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Disconnect40 extends ReceivingPacket {
    private String reason; // json encoded String

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        reason = readString(buff);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}