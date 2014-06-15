package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class ReceivingPacket {

    public static byte[] bytesFromStream(DataInputStream in) {
        short length = 0;
        try {
            length = in.readShort();
            byte[] arr = new byte[length];
            in.readFully(arr);
            return arr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract void read(DataInputStream in, int len, ServerHandler handler);

    public String readString(DataInputStream in) throws IOException {
        int length = readVarInt(in);
        byte[] stringBytes = new byte[length];
        in.readFully(stringBytes);
        return new String(stringBytes, "UTF-8");
    }

    public int readVarInt(DataInputStream ins) throws IOException {
        int i = 0;
        int j = 0;
        while (true){
            int k = ins.read();

            i |= (k & 0x7F) << j++ * 7;

            if (j > 5) throw new RuntimeException("VarInt too big");

            if ((k & 0x80) != 128) break;
        }

        return i;
    }
}
