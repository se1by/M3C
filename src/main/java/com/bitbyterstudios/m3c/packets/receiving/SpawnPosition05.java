package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class SpawnPosition05 extends ReceivingPacket {

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            int x = in.readInt();
            int y = in.readInt();
            int z = in.readInt();
            System.out.println("We'll spawn at x=" + x + ", y=" + y + ", z=" + z);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        long val = buff.getLong();
        int x = (int) (val >> 38);
        int y = (int) ((val >> 26) & 0xFFF);
        int z = (int) (val << 38 >> 38);
        System.out.println("We'll spawn at x=" + x + ", y=" + y + ", z=" + z);
    }
}
