import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.packet_handler.receiving.Chat02;
import com.bitbyterstudios.m3c.plugin.Listener;
import com.bitbyterstudios.m3c.plugin.Plugin;
import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class HelloWorldPlugin implements Plugin {

    public void onEnable(Client client) {
        Client.getLogger().info("Hello World from your plugin!");
        client.getPluginManager().addListener(Chat02.class, new Listener() {
            public ByteBuffer handle(ByteBuffer buffer) {
                System.out.println("I got a buffer!");
                ByteBuffer clone = buffer; // fuck it«
                int length = Utilities.readVarInt(clone);
                byte[] stringBytes = new byte[length];
                clone.get(stringBytes, 0, length);
                String msg = new String(stringBytes, Charset.forName("UTF-8"));
                System.out.println("Message: " + msg + " of type " + clone.get());
                return buffer;
            }
        });
    }

    public String getName() {
        return getClass().getName();
    }
}
