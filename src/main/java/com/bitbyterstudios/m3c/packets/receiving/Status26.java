package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.sending.Status22;

import java.nio.ByteBuffer;

public class Status26 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        int eId = buff.getInt();
        byte status = buff.get();

        //Client.getLogger().fine(String.format("eId %d, status %d", eId, status));

        if (eId == handler.getData().getEntityId() && status == 3) { //We are dead, lets respawn!
            Client.getLogger().fine("We died :o Better respawn...");
            Status22 status22 = new Status22();
            status22.create();
            handler.addPacketToSend(status22);
        }
    }
}
