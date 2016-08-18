package com.bitbyterstudios.m3c.packet_handler.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Time03 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        long age = buff.getLong();
        long time = buff.getLong();

        Client.getLogger().finest("World is " + (age / 24000) + " ingame days old, time is " + (time / 1000 + 6));
    }
}
