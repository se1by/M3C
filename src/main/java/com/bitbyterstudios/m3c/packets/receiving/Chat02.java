package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Chat02 extends ReceivingPacket {
    private String rawJson;
    private byte position;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        rawJson = readString(buff);
        position = buff.get();
    }

    public String getRawJson() {
        return rawJson;
    }

    public void setRawJson(String rawJson) {
        this.rawJson = rawJson;
    }

    public byte getPosition() {
        return position;
    }

    public void setPosition(byte position) {
        this.position = position;
    }
}
