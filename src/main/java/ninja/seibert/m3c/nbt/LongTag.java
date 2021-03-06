package ninja.seibert.m3c.nbt;

import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class LongTag extends AbstractTag<Long> {

    public LongTag(String name, long value) {
        setType((byte) 4);
        setName(name);
        setValue(value);
    }

    public static LongTag fromByteBuffer(ByteBuffer buffer, boolean named) {
        String name = null;
        if (named) {
            name = Utilities.readShortPrefixedStringFromByteBuffer(buffer);
        }
        long value = buffer.getLong();
        return new LongTag(name, value);
    }
}
