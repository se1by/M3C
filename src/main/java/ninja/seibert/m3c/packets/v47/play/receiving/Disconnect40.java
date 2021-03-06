package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Disconnect40 extends ReceivingPacket {
    private String reason; // json encoded String

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        reason = readString(buff);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
