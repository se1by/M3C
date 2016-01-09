package com.bitbyterstudios.m3c.packet_handler.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packet_handler.sending.Status22;

import java.nio.ByteBuffer;

public class Health06 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        float health = buff.getFloat();
        int food = readVarInt(buff);
        float saturation = buff.getFloat();

        Client.getLogger().fine(String.format("We got %f <3 (food = %d, saturation %f)", health, food, saturation));

        if (health <= 0) {
            Client.getLogger().fine("We are dead :o Better respawn...");
            Status22 status = new Status22();
            status.create();
            handler.addPacketToSend(status);
        }
    }
}