package com.bitbyterstudios.m3c.packet_handler.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Experience31 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        float progress = buff.getFloat();
        int level = readVarInt(buff);
        int total = readVarInt(buff);

        Client.getLogger().fine(String.format("We are %f%% in on level %d, resulting in a total of %d experience!",
                progress, level, total));
    }
}
