package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.nbt.AbstractTag;
import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class UpdateBlockEntity35 extends ReceivingPacket {
    private int x;
    private int y;
    private int z;
    private byte action;
    private AbstractTag nbtTag;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        int[] position = Utilities.positionFromLong(buff.getLong());
        x = position[0];
        y = position[1];
        z = position[2];
        action = (byte) (buff.get() & 0xFF);
        nbtTag = AbstractTag.fromByteBuffer(buff, true);
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

    public byte getAction() {
        return action;
    }

    public void setAction(byte action) {
        this.action = action;
    }

    public AbstractTag getNbtTag() {
        return nbtTag;
    }

    public void setNbtTag(AbstractTag nbtTag) {
        this.nbtTag = nbtTag;
    }
}
