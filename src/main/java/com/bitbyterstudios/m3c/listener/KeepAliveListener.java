package com.bitbyterstudios.m3c.listener;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.receiving.KeepAlive00;
import com.bitbyterstudios.m3c.plugin.Listener;

public class KeepAliveListener implements Listener<KeepAlive00> {

    @Override
    public void handle(KeepAlive00 packet, ServerHandler handler) {
        handler.addPacketToSend(
                new com.bitbyterstudios.m3c.packets.sending.KeepAlive00(packet.getId())
        );
    }
}
