package com.bitbyterstudios.m3c.packets.v47.login.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class LoginSuccess02 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        String uuid = readString(buff);
        String username = readString(buff);
        Client.getLogger().info("Logged in as " + username);
        Client.getLogger().info("UUID is " + uuid);
    }
}
