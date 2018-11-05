import ninja.seibert.m3c.Client;
import ninja.seibert.m3c.packets.v340.play.receiving.PluginMessage18;
import ninja.seibert.m3c.plugin.Plugin;

import java.nio.charset.Charset;

public class HelloWorldPlugin implements Plugin {

    public void onEnable(Client client) {
        Client.getLogger().info("Hello World from your plugin!");
        client.getPluginManager().addListener(PluginMessage18.class, (packet, server) -> {
            PluginMessage18 msg = ((PluginMessage18) packet);
            server.getLogger().info("Received plugin message on channel " + msg.getChannel());
            server.getLogger().info("Data as String: " + new String(msg.getData(), Charset.forName("UTF-8")));
        });
    }

    public String getName() {
        return getClass().getName();
    }
}
