package ninja.seibert.m3c.listener;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.definitions.play.receiving.KeepAlive;
import ninja.seibert.m3c.packets.v340.play.sending.KeepAlive0B;
import ninja.seibert.m3c.plugin.Listener;

public class KeepAliveListener implements Listener<KeepAlive> {

    @Override
    public void handle(KeepAlive packet, ConnectionHandler handler) {
        switch (handler.getProtocol().getVersion()) {
            case 47:
                handler.addPacketToSend(
                        new ninja.seibert.m3c.packets.v47.play.sending.KeepAlive00(packet.getId())
                );
                break;
            case 340:
                handler.addPacketToSend(
                        new KeepAlive0B(packet.getId())
                );
                break;
            default:
                throw new IllegalStateException("KeepAliveListener doesn't know about version " + handler.getProtocol().getVersion() + "!");
        }
    }
}
