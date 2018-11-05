package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class DisplayScoreboard3B extends ReceivingPacket {
    private byte position;
    private String scoreName;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.position = buff.get();
        this.scoreName = readString(buff);
    }

    public byte getPosition() {
        return position;
    }

    public String getScoreName() {
        return scoreName;
    }

}
