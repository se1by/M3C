package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class KeepAlive00 extends ReceivingPacket {

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            int id = in.readInt();
            com.bitbyterstudios.m3c.packets.sending.KeepAlive00 response
                    = new com.bitbyterstudios.m3c.packets.sending.KeepAlive00();
            response.setId(id);
            response.create();
            handler.addPacketToSend(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
