package com.bitbyterstudios.m3c.nbt;

import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class ShortTag extends AbstractTag<Short> {

    public ShortTag(String name, short value) {
        setType((byte) 2);
        setName(name);
        setValue(value);
    }

    public static ShortTag fromByteBuffer(ByteBuffer buffer) {
        String name = Utilities.readStringFromByteBuffer(buffer);
        short value = buffer.getShort();
        return new ShortTag(name, value);
    }
}
