package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SpawnPosition05 extends ReceivingPacket {
    private int x; //26 bit integer
    private int y; //12 bit integer
    private int z; //26 bit integer

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        long val = buff.getLong();
        x = (int) (val >> 38);
        y = (int) ((val >> 26) & 0xFFF);
        z = (int) (val << 38 >> 38);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
