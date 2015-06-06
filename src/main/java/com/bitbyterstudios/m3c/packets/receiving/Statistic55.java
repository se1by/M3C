package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Statistic55 extends ReceivingPacket {

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
