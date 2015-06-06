package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class PlayerListItem56 extends ReceivingPacket {
    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            String name = readString(in);
            boolean online = in.readBoolean();
            short ping = in.readShort();

            Client.getLogger().finest(name + " is " + (online ? "" : "not ") + "online (ping " + ping + "ms)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        String name = readString(buff);
        boolean online = buff.get() != 0;
        short ping = buff.getShort();

        Client.getLogger().finest(name + " is " + (online ? "" : "not ") + "online (ping " + ping + "ms)");
    }
}
