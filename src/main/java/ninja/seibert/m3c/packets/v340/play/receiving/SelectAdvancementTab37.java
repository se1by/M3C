package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SelectAdvancementTab37 extends ReceivingPacket {
    private String identifier;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        if (buff.get() == 1) {
            this.identifier = readString(buff);
        }
    }

    public String getIdentifier() {
        return identifier;
    }
}
