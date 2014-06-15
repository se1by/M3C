package com.bitbyterstudios.m3c.packets.receiving;

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
            System.out.println("Our entityID is " + entityId);
            System.out.println("Our gamemode is " + gamemode);
            System.out.println("We are in dimension " + dimension);
            System.out.println("We play at difficulty " + difficulty);
            System.out.println("Max players is " + maxPlayers);
            System.out.println("Level type is " + levelType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
