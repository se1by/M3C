package com.bitbyterstudios.m3c.plugin;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

public interface Listener<T extends ReceivingPacket> {
    void handle(T packet, ServerHandler server);
}
