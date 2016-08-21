package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;
import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class BlockAction24 extends ReceivingPacket {
    private int x;
    private int y;
    private int z;
    private byte action1;
    private byte action2;
    private int blockType;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        long val = buff.getLong();
        int[] position = Utilities.positionFromLong(val);
        x = position[0];
        y = position[1];
        z = position[2];
        action1 = buff.get();
        action2 = buff.get();
        blockType = readVarInt(buff);
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

    public byte getAction1() {
        return action1;
    }

    public void setAction1(byte action1) {
        this.action1 = action1;
    }

    public byte getAction2() {
        return action2;
    }

    public void setAction2(byte action2) {
        this.action2 = action2;
    }

    public int getBlockType() {
        return blockType;
    }

    public void setBlockType(int blockType) {
        this.blockType = blockType;
    }
}
