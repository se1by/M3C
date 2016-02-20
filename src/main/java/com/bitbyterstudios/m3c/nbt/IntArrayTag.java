package com.bitbyterstudios.m3c.nbt;

import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class IntArrayTag extends AbstractTag<int[]> {

    public IntArrayTag(String name, int[] value) {
        setType((byte) 11);
        setName(name);
        setValue(value);
    }

    public static IntArrayTag fromByteBuffer(ByteBuffer buffer, boolean named) {
        String name = null;
        if (named) {
            name = Utilities.readShortPrefixedStringFromByteBuffer(buffer);
        }
        int length = buffer.getInt();
        int[] value = new int[length];
        for (int i = 0; i < length; i++) {
            value[i] = buffer.getInt();
        }
        return new IntArrayTag(name, value);
    }
}
