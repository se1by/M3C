package com.bitbyterstudios.m3c.util;

import com.bitbyterstudios.m3c.nbt.AbstractTag;
import com.bitbyterstudios.m3c.nbt.EndTag;

import java.nio.ByteBuffer;

public class Slot {
    private short itemId;
    private byte count;
    private short damage;
    private AbstractTag nbtTag;

    public Slot(short itemId, byte count, short damage, AbstractTag nbtTag) {
        this.itemId = itemId;
        this.count = count;
        this.damage = damage;
        this.nbtTag = nbtTag;
    }

    public short getItemId() {
        return itemId;
    }

    public void setItemId(short itemId) {
        this.itemId = itemId;
    }

    public byte getCount() {
        return count;
    }

    public void setCount(byte count) {
        this.count = count;
    }

    public short getDamage() {
        return damage;
    }

    public void setDamage(short damage) {
        this.damage = damage;
    }

    public AbstractTag getNbtTag() {
        return nbtTag;
    }

    public void setNbtTag(AbstractTag nbtTag) {
        this.nbtTag = nbtTag;
    }

    public static Slot fromByteBuffer(ByteBuffer buffer) {
        short itemId = buffer.getShort();
        if (itemId == -1) { // Empty slot
            return new Slot(itemId, (byte) 0, (short) 0, new EndTag());
        }
        byte count = buffer.get();
        short damage = buffer.getShort();
        return new Slot(itemId, count, damage, AbstractTag.fromByteBuffer(buffer, true));
    }
}
