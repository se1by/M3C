package com.bitbyterstudios.m3c.nbt;

import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class IntTag extends AbstractTag<Integer> {

    public IntTag(String name, int value) {
        setType((byte) 3);
        setName(name);
        setValue(value);
    }

    public static IntTag fromByteBuffer(ByteBuffer buffer) {
        String name = Utilities.readStringFromByteBuffer(buffer);
        int value = buffer.getInt();
        return new IntTag(name, value);
    }
}
