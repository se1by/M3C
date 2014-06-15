package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class Disconnect00 extends ReceivingPacket {
    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            String reason = readString(in);
            System.out.println("Kicked for " + reason);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
