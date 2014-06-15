package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class HeldItemChange09 extends ReceivingPacket {

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            byte slot = in.readByte();
            Client.getLogger().fine("We got slot " + slot + " selected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
