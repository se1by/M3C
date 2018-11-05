package ninja.seibert.m3c.packets;

import ninja.seibert.m3c.ConnectionHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.UUID;

public abstract class ReceivingPacket {

    public static byte[] bytesFromStream(DataInputStream in) {
        int length;
        try {
            length = readVarInt(in);
            byte[] arr = new byte[length];
            in.readFully(arr);
            return arr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] bytesFromBuff(ByteBuffer buff) {
        int length = readVarInt(buff);
        byte[] bytes = new byte[length];
        buff.get(bytes, 0, length);
        return bytes;
    }

    public abstract void handle(ByteBuffer buff, ConnectionHandler handler);

    public String readString(DataInputStream in) throws IOException {
        int length = readVarInt(in);
        byte[] stringBytes = new byte[length];
        in.readFully(stringBytes);
        return new String(stringBytes, "UTF-8");
    }

    public String readString(ByteBuffer buff) {
        int length = readVarInt(buff);
        byte[] stringBytes = new byte[length];
        buff.get(stringBytes, 0, length);
        return new String(stringBytes, Charset.forName("UTF-8"));
    }

    public UUID readUUID(ByteBuffer buffer) {
        long most = buffer.getLong();
        long least = buffer.getLong();
        return new UUID(most, least);
    }

    public static int readVarInt(DataInputStream ins) throws IOException {
        int i = 0;
        int j = 0;
        while (true) {
            int k = ins.read();

            i |= (k & 0x7F) << j++ * 7;

            if (j > 5) throw new RuntimeException("VarInt too big");

            if ((k & 0x80) != 128) break;
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

    public static long readVarLong(ByteBuffer buff) {
        long i = 0;
        int j = 0;
        while (true) {
            int k = buff.get();

            i |= (k & 0x7F) << j++ * 7;
            if (j > 10) throw new RuntimeException("VarLong too big");

            if ((k & 0x80) != 128) break; //MSB not set? 0x80 = 1000 0000(b)
        }

        return i;
    }

    public static long otherReadVarLong(ByteBuffer buffer) {
        int numRead = 0;
        long result = 0;
        byte read;
        do {
            read = buffer.get();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 10) {
                throw new RuntimeException("VarLong is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }
}
