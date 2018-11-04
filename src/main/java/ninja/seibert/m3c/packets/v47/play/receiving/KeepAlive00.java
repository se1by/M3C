package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class KeepAlive00 extends ReceivingPacket {
    private int id;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        id = readVarInt(buff);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
