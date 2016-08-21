package com.bitbyterstudios.m3c.packets.v47.login.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Disconnect00 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        String reason = readString(buff);
        Client.getLogger().warning("Kicked for " + reason);
    }
}
