package ninja.seibert.m3c.listener;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.v47.play.receiving.KeepAlive00;
import ninja.seibert.m3c.plugin.Listener;

public class KeepAliveListener implements Listener<KeepAlive00> {

    @Override
    public void handle(KeepAlive00 packet, ConnectionHandler handler) {
        handler.addPacketToSend(
                new ninja.seibert.m3c.packets.v47.play.sending.KeepAlive00(packet.getId())
        );
    }
}
