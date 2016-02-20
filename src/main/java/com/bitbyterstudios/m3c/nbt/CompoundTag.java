package com.bitbyterstudios.m3c.nbt;

import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class CompoundTag extends AbstractTag<List<AbstractTag>> {

    public CompoundTag(String name, List<AbstractTag> value) {
        setType((byte) 10);
        setName(name);
        setValue(value);
    }

    public static CompoundTag fromByteBuffer(ByteBuffer buffer) {
        String name = Utilities.readStringFromByteBuffer(buffer);
        List<AbstractTag> value = new ArrayList<>();
        value.add(AbstractTag.fromByteBuffer(buffer));
        while (value.get(value.size()-1).getType() != 0) {
            value.add(AbstractTag.fromByteBuffer(buffer));
        }
        return new CompoundTag(name, value);
    }
}
