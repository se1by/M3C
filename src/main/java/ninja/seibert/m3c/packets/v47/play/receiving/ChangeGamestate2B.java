package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class ChangeGamestate2B extends ReceivingPacket {
    private byte reason;
    private float value; // best name ever

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        reason = (byte) (buff.get() & 0xFF);
        value = buff.getFloat();
    }

    public byte getReason() {
        return reason;
    }

    public void setReason(byte reason) {
        this.reason = reason;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
