package ninja.seibert.m3c.plugin;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

public interface Listener<T extends ReceivingPacket> {
    void handle(T packet, ConnectionHandler server);
}
