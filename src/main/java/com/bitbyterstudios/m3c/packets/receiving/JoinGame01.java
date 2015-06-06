package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class JoinGame01 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        int entityId = buff.getInt();
        int gamemode = buff.get() & 0xFF;
        byte dimension = buff.get();
        int difficulty = buff.get() & 0xFF;
        int maxPlayers = buff.get() & 0xFF;
        String levelType = readString(buff);
        handler.getData().setEntityId(entityId);
        Client.getLogger().fine("Our entityID is " + entityId);
        Client.getLogger().fine("Our gamemode is " + gamemode);
        Client.getLogger().fine("We are in dimension " + dimension);
        Client.getLogger().fine("We play at difficulty " + difficulty);
        Client.getLogger().fine("Max players is " + maxPlayers);
        Client.getLogger().fine("Level type is " + levelType);
    }
}
