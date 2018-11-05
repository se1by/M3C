package ninja.seibert.m3c.listener;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.definitions.play.receiving.Disconnect;
import ninja.seibert.m3c.plugin.Listener;

public class DisconnectListener implements Listener<Disconnect> {

    @Override
    public void handle(Disconnect packet, ConnectionHandler handler) {
        handler.getLogger().info("Disconnected: " + packet.getReason());
        handler.stop();
    }
}
