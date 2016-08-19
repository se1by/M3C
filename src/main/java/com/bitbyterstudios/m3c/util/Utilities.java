package com.bitbyterstudios.m3c.util;

import com.google.common.io.ByteArrayDataOutput;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Utilities {

    public static boolean getBit(byte toCheck, int position) {
        return (toCheck >> position & 1) == 1;
    }


    public static int readVarInt(DataInputStream ins) throws IOException {
        int i = 0;
        int j = 0;
        while (true){
            int k = ins.read();

            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) throw new RuntimeException("VarInt too big");

            if ((k & 0x80) != 128) break; //MSB not set? 0x80 = 1000 0000(b)
        }

        return i;
    }

    public static int readVarInt(ByteBuffer buff) {
        int i = 0;
        int j = 0;
        while (true) {
            int k = buff.get();

            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) throw new RuntimeException("VarInt too big");

            if ((k & 0x80) != 128) break; //MSB not set? 0x80 = 1000 0000(b)
        }

        return i;
    }

    public static int readVarInt(byte[] b) {
        int i = 0;
        int j = 0;
        for (byte k : b) {
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) throw new RuntimeException("VarInt too big");

            if ((k & 0x80) != 128) break; //MSB not set? 0x80 = 1000 0000(b)
        }

        return i;
    }

    public static void writeVarInt(ByteArrayDataOutput buff, int paramInt) {
        while (true) {
            if ((paramInt & 0xFFFFFF80) == 0) {
                buff.writeByte((byte) paramInt);
                return;
            }

            buff.writeByte((byte) (paramInt & 0x7F | 0x80));
            paramInt >>>= 7;
        }
    }

    public static int getVarIntWidth(int varInt) { //should be cheaper than log 2 (varint)
        int i = 1;
        while ((varInt & 0xFFFFFF80) != 0) {
            varInt >>>= 7;
            i++;
        }
        return i;
    }

    public static String readShortPrefixedStringFromByteBuffer(ByteBuffer buffer) {
        short length = buffer.getShort();
        byte[] stringBytes = new byte[length];
        buffer.get(stringBytes);
        return new String(stringBytes);
    }

    public static String readVarIntPrefixedStringFromByteBuffer(ByteBuffer buffer) {
        int length = readVarInt(buffer);
        byte[] stringBytes = new byte[length];
        buffer.get(stringBytes);
        return new String(stringBytes);
    }

    public static ByteBuffer clone(ByteBuffer original) {
        ByteBuffer clone = ByteBuffer.allocate(original.capacity());
        original.rewind();//copy from the beginning
        clone.put(original);
        original.rewind();
        clone.flip();
        return clone;
    }

    public static byte[] compress(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        return outputStream.toByteArray();
    }

    public static int[] positionFromLong(long val) {
        int x = (int) (val >> 38);
        int y = (int) ((val >> 26) & 0xFFF);
        int z = (int) (val << 38 >> 38);
        return new int[] {x, y, z};
    }

    public static Class getGenericType(Class clazz) {
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            if (genericInterface instanceof ParameterizedType) {
                Type[] genericTypes = ((ParameterizedType) genericInterface).getActualTypeArguments();
                if (genericTypes.length > 0) {
                    return ((Class) genericTypes[0]);
                }
            }
        }
        return null;
    }

}
