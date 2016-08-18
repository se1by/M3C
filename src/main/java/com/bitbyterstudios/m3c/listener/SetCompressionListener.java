package com.bitbyterstudios.m3c.listener;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.receiving.SetCompression46;
import com.bitbyterstudios.m3c.plugin.Listener;

public class SetCompressionListener implements Listener<SetCompression46> {
    @Override
    public void handle(SetCompression46 packet, ServerHandler server) {
        server.setCompressionThreshold(packet.getThreshold());
        Client.getLogger().info("Compression threshold set to " + packet.getThreshold() + " bytes!");
    }
}
