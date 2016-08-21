package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class TabComplete3A extends ReceivingPacket {
    private String[] completions;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        int size = readVarInt(buff);
        completions = new String[size];
        for (int i = 0; i < completions.length; i++) {
            completions[i] = readString(buff);
        }
    }

    public String[] getCompletions() {
        return completions;
    }

    public void setCompletions(String[] completions) {
        this.completions = completions;
    }
}
