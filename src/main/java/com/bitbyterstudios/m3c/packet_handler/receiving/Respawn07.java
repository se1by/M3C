package com.bitbyterstudios.m3c.packet_handler.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Respawn07 extends ReceivingPacket { //Name is a bit misleading, it's actually a "change dimension" packet

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        int dimension = buff.getInt();
        int difficulty = buff.get() & 0xFF;
        int gamemode = buff.get() & 0xFF;
        String level = readString(buff);

        Client.getLogger().fine(String.format("We changed into dimension %d, with difficulty %d, gamemode %d on level %s",
                dimension, difficulty, gamemode, level));
    }
}
