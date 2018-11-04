package ninja.seibert.m3c.nbt;

import java.nio.ByteBuffer;

public abstract class AbstractTag<T> {
    private byte type;
    private String name;
    private T value;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public static AbstractTag fromByteBuffer(ByteBuffer buffer, boolean named) {
        return fromByteBuffer(buffer, buffer.get(), named);
    }

    public static AbstractTag fromByteBuffer(ByteBuffer buffer, byte type, boolean named) {
        switch (type) {
            case 0:
                return new EndTag();
            case 1:
                return ByteTag.fromByteBuffer(buffer, named);
            case 2:
                return ShortTag.fromByteBuffer(buffer, named);
            case 3:
                return IntTag.fromByteBuffer(buffer, named);
            case 4:
                return LongTag.fromByteBuffer(buffer, named);
            case 5:
                return FloatTag.fromByteBuffer(buffer, named);
            case 6:
                return DoubleTag.fromByteBuffer(buffer, named);
            case 7:
                return ByteArrayTag.fromByteBuffer(buffer, named);
            case 8:
                return StringTag.fromByteBuffer(buffer, named);
            case 9:
                return ListTag.fromByteBuffer(buffer, named);
            case 10:
                return CompoundTag.fromByteBuffer(buffer, named);
            case 11:
                return IntArrayTag.fromByteBuffer(buffer, named);
            default:
                throw new IllegalArgumentException("Unknown nbt type " + type + "!");
        }
    }
}
