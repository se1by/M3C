package com.bitbyterstudios.m3c.plugin;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

public interface Listener<T extends ReceivingPacket> {
    void handle(T packet, ConnectionHandler server);
}
