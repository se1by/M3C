package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class ResourcepackSend48 extends ReceivingPacket {
    private String url;
    private String hash;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        url = readString(buff);
        hash = readString(buff);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}