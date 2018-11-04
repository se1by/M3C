package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class ResourcepackSend48 extends ReceivingPacket {
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

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
