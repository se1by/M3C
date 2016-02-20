package com.bitbyterstudios.m3c.nbt;

import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class FloatTag extends AbstractTag<Float> {

    public FloatTag(String name, float value) {
        setType((byte) 5);
        setName(name);
        setValue(value);
    }

    public static FloatTag fromByteBuffer(ByteBuffer buffer, boolean named) {
        String name = null;
        if (named) {
            name = Utilities.readStringFromByteBuffer(buffer);
        }
        float value = buffer.getFloat();
        return new FloatTag(name, value);
    }
}
