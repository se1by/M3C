import ninja.seibert.m3c.Client;
import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.v47.play.receiving.Chat02;
import ninja.seibert.m3c.plugin.Plugin;

public class HelloWorldPlugin implements Plugin {

    public void onEnable(Client client) {
        Client.getLogger().info("Hello World from your plugin!");
        client.getPluginManager().addListener(Chat02.class, (Chat02 packet, ConnectionHandler server)
                -> Client.getLogger().info("Chat message: " + packet.getRawJson()));
    }

    public String getName() {
        return getClass().getName();
    }
}
