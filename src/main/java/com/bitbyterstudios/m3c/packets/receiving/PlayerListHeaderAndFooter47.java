package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class PlayerListHeaderAndFooter47 extends ReceivingPacket {
    private String header;
    private String footer;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        header = readString(buff);
        footer = readString(buff);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}
