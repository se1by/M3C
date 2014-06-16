package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class JoinGame01 extends ReceivingPacket {

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            int entityId = in.readInt();
            int gamemode = in.readByte() & 0xFF;
            byte dimension = in.readByte();
            int difficulty = in.readByte() & 0xFF;
            int maxPlayers = in.readByte() & 0xFF;
            String levelType = readString(in);
            handler.getData().setEntityId(entityId);
            Client.getLogger().fine("Our entityID is " + entityId);
            Client.getLogger().fine("Our gamemode is " + gamemode);
            Client.getLogger().fine("We are in dimension " + dimension);
            Client.getLogger().fine("We play at difficulty " + difficulty);
            Client.getLogger().fine("Max players is " + maxPlayers);
            Client.getLogger().fine("Level type is " + levelType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
