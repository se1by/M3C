package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

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

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        int id = buff.getInt();
        com.bitbyterstudios.m3c.packets.sending.KeepAlive00 response
                = new com.bitbyterstudios.m3c.packets.sending.KeepAlive00();
        response.setId(id);
        response.create();
        handler.addPacketToSend(response);
    }
}
