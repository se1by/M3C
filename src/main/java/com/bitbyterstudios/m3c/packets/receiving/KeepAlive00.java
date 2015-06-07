package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class KeepAlive00 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        int id = readVarInt(buff);
        com.bitbyterstudios.m3c.packets.sending.KeepAlive00 response
                = new com.bitbyterstudios.m3c.packets.sending.KeepAlive00();
        response.setId(id);
        response.create();
        handler.addPacketToSend(response);
    }
}
