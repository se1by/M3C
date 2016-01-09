package com.bitbyterstudios.m3c.packet_handler.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class LoginSuccess02 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        String uuid = readString(buff);
        String username = readString(buff);
        Client.getLogger().info("Logged in as " + username);
        Client.getLogger().info("UUID is " + uuid);
    }
}
