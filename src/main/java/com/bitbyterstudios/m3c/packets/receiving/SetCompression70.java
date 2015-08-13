package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class SetCompression70 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        int threshold = readVarInt(buff);
        handler.setCompressionThreshold(threshold);
        Client.getLogger().fine("Compression threshold set to " + threshold);
    }
}
