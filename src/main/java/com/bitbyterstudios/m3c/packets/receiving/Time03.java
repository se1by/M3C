package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class Time03 extends ReceivingPacket {

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            long age = in.readLong();
            long time = in.readLong();

            Client.getLogger().finest("World is " + (age / 24000) + " ingame days old, time is " + (time/1000 + 6));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
