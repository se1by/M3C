import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.plugin.Plugin;

public class HelloWorldPlugin implements Plugin {

    public void onEnable(Client client) {
        Client.getLogger().info("Hello World from your plugin!");
    }

    public String getName() {
        return getClass().getName();
    }
}
