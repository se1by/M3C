package com.bitbyterstudios.m3c.listener;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.v47.login.receiving.SetCompression03;
import com.bitbyterstudios.m3c.plugin.Listener;

public class SetCompressionListener implements Listener<SetCompression03> {
    @Override
    public void handle(SetCompression03 packet, ConnectionHandler server) {
        server.setCompressionThreshold(packet.getThreshold());
        Client.getLogger().info("Compression threshold set to " + packet.getThreshold() + " bytes!");
    }
}
