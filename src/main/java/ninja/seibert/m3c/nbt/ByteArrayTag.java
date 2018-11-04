package ninja.seibert.m3c.nbt;

import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class ByteArrayTag extends AbstractTag<byte[]> {

    public ByteArrayTag(String name, byte[] value) {
        setType((byte) 7);
        setName(name);
        setValue(value);
    }

    public static ByteArrayTag fromByteBuffer(ByteBuffer buffer, boolean named) {
        String name = null;
        if (named) {
            name = Utilities.readShortPrefixedStringFromByteBuffer(buffer);
        }
        int length = buffer.getInt();
        byte[] value = new byte[length];
        buffer.get(value);
        return new ByteArrayTag(name, value);
    }
}
