package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.sending.Status22;

import java.io.DataInputStream;
import java.io.IOException;

public class Status26 extends ReceivingPacket {
    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            int eId = in.readInt();
            byte status = in.readByte();

            //Client.getLogger().fine(String.format("eId %d, status %d", eId, status));

            if (eId == handler.getData().getEntityId() && status == 3) { //We are dead, lets respawn!
                Client.getLogger().fine("We died :o Better respawn...");
                Status22 status22 = new Status22();
                status22.create();
                handler.addPacketToSend(status22);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
