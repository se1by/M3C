package com.bitbyterstudios.m3c.util;

import java.nio.ByteBuffer;

public class Slot {
    private short itemId;
    private byte count;
    private short damage;
    private byte[] raw_nbt;

    public Slot(short itemId, byte count, short damage, byte[] raw_nbt) {
        this.itemId = itemId;
        this.count = count;
        this.damage = damage;
        this.raw_nbt = raw_nbt;
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

    public byte[] getRaw_nbt() {
        return raw_nbt;
    }

    public void setRaw_nbt(byte[] raw_nbt) {
        this.raw_nbt = raw_nbt;
    }

    public static Slot fromByteBuffer(ByteBuffer buffer) {
        short itemId = buffer.getShort();
        if (itemId == -1) { // Empty slot
            return new Slot(itemId, (byte) 0, (short) 0, null);
        }
        byte count = buffer.get();
        short damage = buffer.getShort();
        byte first_nbt = buffer.get();
        if (first_nbt == 0) { // No nbt data
            return new Slot(itemId, count, damage, new byte[]{first_nbt});
        }
        byte[] nbt_data = new byte[buffer.remaining() + 1];
        nbt_data[0] = first_nbt;
        for (int i = 1; buffer.hasRemaining(); i++) {
            nbt_data[i] = buffer.get();
        }
        return new Slot(itemId, count, damage, nbt_data);
    }
}
