package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class ServerDifficulty41 extends ReceivingPacket {
    private byte difficulty;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        difficulty = (byte) (buff.get() & 0xFF);
    }

    public byte getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(byte difficulty) {
        this.difficulty = difficulty;
    }
}
