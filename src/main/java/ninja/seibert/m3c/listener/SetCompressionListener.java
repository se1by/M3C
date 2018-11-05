package ninja.seibert.m3c.listener;

import ninja.seibert.m3c.Client;
import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.definitions.login.receiving.SetCompression;
import ninja.seibert.m3c.plugin.Listener;

public class SetCompressionListener implements Listener<SetCompression> {
    @Override
    public void handle(SetCompression packet, ConnectionHandler server) {
        server.setCompressionThreshold(packet.getThreshold());
        Client.getLogger().info("Compression threshold set to " + packet.getThreshold() + " bytes!");
    }
}
