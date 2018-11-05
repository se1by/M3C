package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class ResourcepackSend34 extends ReceivingPacket {
    private String url;
    private String hash;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        url = readString(buff);
        hash = readString(buff);
    }

    public String getUrl() {
        return url;
    }

    public String getHash() {
        return hash;
    }

}
