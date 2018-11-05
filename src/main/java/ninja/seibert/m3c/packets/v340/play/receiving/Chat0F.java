package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Chat0F extends ReceivingPacket {
    private String rawJson;
    private byte position;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.rawJson = readString(buff);
        this.position = buff.get();
    }

    public String getRawJson() {
        return rawJson;
    }

    public byte getPosition() {
        return position;
    }

}
