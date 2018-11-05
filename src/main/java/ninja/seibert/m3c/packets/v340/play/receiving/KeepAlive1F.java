package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.definitions.play.receiving.KeepAlive;

import java.nio.ByteBuffer;

public class KeepAlive1F extends KeepAlive {
    private long id;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        id = buff.getLong();
    }

    public long getId() {
        return id;
    }

}
