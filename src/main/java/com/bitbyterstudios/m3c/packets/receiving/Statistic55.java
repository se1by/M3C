package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Statistic55 extends ReceivingPacket {

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            Client.getLogger().fine("A few statistics for you: ");
            int count = readVarInt(in);
            while (count > 0) {
                String statistic = readString(in);
                int value = readVarInt(in);

                Client.getLogger().fine(statistic + ": " + value);
                count--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        Client.getLogger().fine("A few statistics for you: ");
        int count = readVarInt(buff);
        while (count > 0) {
            String statistic = readString(buff);
            int value = readVarInt(buff);

            Client.getLogger().fine(statistic + ": " + value);
            count--;
        }
    }
}
