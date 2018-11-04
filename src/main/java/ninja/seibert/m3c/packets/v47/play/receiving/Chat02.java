package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Chat02 extends ReceivingPacket {
    private String rawJson;
    private byte position;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        rawJson = readString(buff);
        position = buff.get();
    }

    public String getRawJson() {
        return rawJson;
    }

    public void setRawJson(String rawJson) {
        this.rawJson = rawJson;
    }

    public byte getPosition() {
        return position;
    }

    public void setPosition(byte position) {
        this.position = position;
    }
}
