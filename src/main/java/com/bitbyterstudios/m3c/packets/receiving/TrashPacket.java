package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class TrashPacket extends ReceivingPacket {
    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            byte[] trash = new byte[len];
            in.readFully(trash);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
