import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.receiving.Chat02;
import com.bitbyterstudios.m3c.plugin.Listener;
import com.bitbyterstudios.m3c.plugin.Plugin;

public class HelloWorldPlugin implements Plugin {

    public void onEnable(Client client) {
        Client.getLogger().info("Hello World from your plugin!");
        client.getPluginManager().addListener(Chat02.class, new Listener<Chat02>() {
            public void handle(Chat02 packet, ServerHandler server) {
                Client.getLogger().info("Chat message: " + packet.getRawJson());
            }
        });
    }

    public String getName() {
        return getClass().getName();
    }
}
