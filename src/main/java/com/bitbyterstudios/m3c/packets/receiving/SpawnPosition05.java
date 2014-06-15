package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;

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
}
