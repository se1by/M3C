package com.bitbyterstudios.m3c.nbt;

import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class StringTag extends AbstractTag<String> {

    public StringTag(String name, String value) {
        setType((byte) 8);
        setName(name);
        setValue(value);
    }

    public static StringTag fromByteBuffer(ByteBuffer buffer, boolean named) {
        String name = null;
        if (named) {
            name = Utilities.readShortPrefixedStringFromByteBuffer(buffer);
        }
        String value = Utilities.readShortPrefixedStringFromByteBuffer(buffer);
        return new StringTag(name, value);
    }
}
