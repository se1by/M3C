package com.bitbyterstudios.m3c.nbt;

import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class DoubleTag extends AbstractTag<Double> {

    public DoubleTag(String name, double value) {
        setType((byte) 6);
        setName(name);
        setValue(value);
    }

    public static DoubleTag fromByteBuffer(ByteBuffer buffer) {
        String name = Utilities.readStringFromByteBuffer(buffer);
        double value = buffer.getDouble();
        return new DoubleTag(name, value);
    }
}
