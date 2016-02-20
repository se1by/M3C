package com.bitbyterstudios.m3c.nbt;

import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ListTag extends AbstractTag<List<AbstractTag>> {


    public ListTag(String name, List<AbstractTag> value) {
        setType((byte) 8);
        setName(name);
        setValue(value);
    }

    public static ListTag fromByteBuffer(ByteBuffer buffer, boolean named) {
        String name = null;
        if (named) {
            name = Utilities.readShortPrefixedStringFromByteBuffer(buffer);
        }
        byte type = buffer.get();
        int length = buffer.getInt();
        List<AbstractTag> value = new ArrayList<>();
        for (; length > 0; length--) {
            value.add(AbstractTag.fromByteBuffer(buffer, type, false));
        }
        return new ListTag(name, value);
    }

}
