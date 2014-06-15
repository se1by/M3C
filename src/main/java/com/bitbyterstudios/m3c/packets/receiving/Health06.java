package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class Health06 extends ReceivingPacket {
    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            float health = in.readFloat();
            short food = in.readShort();
            float saturation = in.readFloat();

            Client.getLogger().fine(String.format("We got %f <3 (food = %s, saturation %f)", health, food, saturation));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
