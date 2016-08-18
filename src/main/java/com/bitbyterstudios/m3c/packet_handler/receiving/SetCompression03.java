package com.bitbyterstudios.m3c.packet_handler.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class SetCompression03 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        int threshold = readVarInt(buff);
        Client.getLogger().info("Compression threshold set to " + threshold + " bytes");
        handler.setCompressionThreshold(threshold);
    }
}
