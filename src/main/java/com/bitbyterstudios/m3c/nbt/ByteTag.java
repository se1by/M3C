package com.bitbyterstudios.m3c.nbt;

import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class ByteTag extends AbstractTag<Byte> {

    public ByteTag(String name, byte value) {
        setType((byte) 1);
        setName(name);
        setValue(value);
    }

    public static ByteTag fromByteBuffer(ByteBuffer buffer) {
        String name = Utilities.readStringFromByteBuffer(buffer);
        byte value = buffer.get();
        return new ByteTag(name, value);
    }
}
