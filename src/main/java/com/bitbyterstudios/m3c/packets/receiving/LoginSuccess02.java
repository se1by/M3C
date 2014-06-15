package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class LoginSuccess02 extends ReceivingPacket {
    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            String uuid = readString(in);
            String username = readString(in);
            System.out.println("Logged in as " + username);
            System.out.println("UUID is " + uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
