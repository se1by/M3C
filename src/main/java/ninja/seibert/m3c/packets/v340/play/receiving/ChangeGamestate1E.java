package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class ChangeGamestate1E extends ReceivingPacket {
    private short reason;
    private float value;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        reason = (short) (buff.get() & 0xFF);
        value = buff.getFloat();
    }

    public short getReason() {
        return reason;
    }

    public float getValue() {
        return value;
    }

}
