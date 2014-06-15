package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class PlayerListItem56 extends ReceivingPacket {
    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            String name = readString(in);
            boolean online = in.readBoolean();
            short ping = in.readShort();

            Client.getLogger().fine(name + " is " + (online? "" : "not ") + "online (ping " + ping + "ms)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
