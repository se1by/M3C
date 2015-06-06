package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.sending.Status22;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Health06 extends ReceivingPacket {
    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            float health = in.readFloat();
            short food = in.readShort();
            float saturation = in.readFloat();

            Client.getLogger().fine(String.format("We got %f <3 (food = %s, saturation %f)", health, food, saturation));

            if (health <= 0) {
                Client.getLogger().fine("We are dead :o Better respawn...");
                Status22 status = new Status22();
                status.create();
                handler.addPacketToSend(status);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
