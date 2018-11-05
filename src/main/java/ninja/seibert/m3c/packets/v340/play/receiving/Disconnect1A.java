package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.definitions.play.receiving.Disconnect;

import java.nio.ByteBuffer;

public class Disconnect1A extends Disconnect {
    private String reason; // json encoded String

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        reason = readString(buff);
    }

    public String getReason() {
        return reason;
    }
}
