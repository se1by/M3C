package ninja.seibert.m3c.listener;

import ninja.seibert.m3c.Client;
import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.v47.login.receiving.SetCompression03;
import ninja.seibert.m3c.plugin.Listener;

public class SetCompressionListener implements Listener<SetCompression03> {
    @Override
    public void handle(SetCompression03 packet, ConnectionHandler server) {
        server.setCompressionThreshold(packet.getThreshold());
        Client.getLogger().info("Compression threshold set to " + packet.getThreshold() + " bytes!");
    }
}
