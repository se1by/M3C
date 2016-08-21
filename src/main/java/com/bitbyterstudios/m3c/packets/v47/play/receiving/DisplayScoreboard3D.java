package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class DisplayScoreboard3D extends ReceivingPacket {
    private byte position;
    private String scoreName;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        position = buff.get();
        scoreName = readString(buff);
    }

    public byte getPosition() {
        return position;
    }

    public void setPosition(byte position) {
        this.position = position;
    }

    public String getScoreName() {
        return scoreName;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }
}
