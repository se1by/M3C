package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Experience31 extends ReceivingPacket {

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            float progress = in.readFloat();
            short level = in.readShort();
            short total = in.readShort();

            Client.getLogger().fine(String.format("We are %f%% in on level %s, resulting in a total of %s experience!",
                    progress, level, total));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        float progress = buff.getFloat();
        int level = readVarInt(buff);
        int total = readVarInt(buff);

        Client.getLogger().fine(String.format("We are %f%% in on level %d, resulting in a total of %d experience!",
                progress, level, total));
    }
}
