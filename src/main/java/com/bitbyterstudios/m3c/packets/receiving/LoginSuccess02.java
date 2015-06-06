package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class LoginSuccess02 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        String uuid = readString(buff);
        String username = readString(buff);
        System.out.println("Logged in as " + username);
        System.out.println("UUID is " + uuid);
    }
}
