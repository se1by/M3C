package com.bitbyterstudios.m3c.nbt;

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

    public static AbstractTag fromByteBuffer(ByteBuffer buffer) {
        return fromByteBuffer(buffer, buffer.get());
    }

    public static AbstractTag fromByteBuffer(ByteBuffer buffer, byte type) {
        switch (type) {
            case 0:
                return new EndTag();
            case 1:
                return ByteTag.fromByteBuffer(buffer);
            case 2:
                return ShortTag.fromByteBuffer(buffer);
            case 3:
                return IntTag.fromByteBuffer(buffer);
            case 4:
                return LongTag.fromByteBuffer(buffer);
            case 5:
                return FloatTag.fromByteBuffer(buffer);
            case 6:
                return DoubleTag.fromByteBuffer(buffer);
            case 7:
                return ByteArrayTag.fromByteBuffer(buffer);
            case 8:
                return StringTag.fromByteBuffer(buffer);
            case 9:
                return ListTag.fromByteBuffer(buffer);
            case 10:
                return CompoundTag.fromByteBuffer(buffer);
            case 11:
                return IntArrayTag.fromByteBuffer(buffer);
            default:
                throw new IllegalArgumentException("Unknown nbt type " + type + "!");
        }
    }
}
