package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class PlayerListHeaderAndFooter4A extends ReceivingPacket {
    private String header;
    private String footer;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.header = readString(buff);
        this.footer = readString(buff);
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

}
