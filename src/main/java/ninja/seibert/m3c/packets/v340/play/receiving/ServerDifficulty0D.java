package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class ServerDifficulty0D extends ReceivingPacket {
    private byte difficulty;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        difficulty = (byte) (buff.get() & 0xFF);
    }

    public byte getDifficulty() {
        return difficulty;
    }

}
