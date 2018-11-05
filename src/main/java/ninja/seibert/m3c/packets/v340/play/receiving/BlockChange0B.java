package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class BlockChange0B extends ReceivingPacket {
    private int x;
    private int y;
    private int z;
    private int blockId;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        int[] position = Utilities.positionFromLong(buff.getLong());
        x = position[0];
        y = position[1];
        z = position[2];
        blockId = readVarInt(buff);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getBlockId() {
        return blockId;
    }

}
