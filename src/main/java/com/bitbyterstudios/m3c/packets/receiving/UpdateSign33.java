package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class UpdateSign33 extends ReceivingPacket {
    private int x;
    private int y;
    private int z;
    private String line1; // json encoded chat
    private String line2;
    private String line3;
    private String line4;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        int[] position = Utilities.positionFromLong(buff.getLong());
        x = position[0];
        y = position[1];
        z = position[2];
        line1 = readString(buff);
        line2 = readString(buff);
        line3 = readString(buff);
        line4 = readString(buff);
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

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getLine4() {
        return line4;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }
}
