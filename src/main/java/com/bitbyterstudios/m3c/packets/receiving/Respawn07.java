package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class Respawn07 extends ReceivingPacket { //Name is a bit misleading, it's actually a "change dimension" packet
    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            int dimension = in.readInt();
            int difficulty = in.readByte() & 0xFF;
            int gamemode = in.readByte() & 0xFF;
            String level = readString(in);

            Client.getLogger().fine(String.format("We changed into dimension %d, with difficulty %d, gamemode %d on level %s",
                    dimension, difficulty, gamemode, level));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
