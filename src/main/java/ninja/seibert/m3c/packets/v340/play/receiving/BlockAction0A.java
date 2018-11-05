package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class BlockAction0A extends ReceivingPacket {
    private int x;
    private int y;
    private int z;
    private short action1;
    private short action2;
    private int blockType;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        int[] position = Utilities.positionFromLong(buff.getLong());
        this.x = position[0];
        this.y = position[1];
        this.z = position[2];

        this.action1 = (short) (buff.get() & 0xFF);
        this.action2 = buff.get();
        this.blockType = readVarInt(buff);
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

    public short getAction1() {
        return action1;
    }

    public short getAction2() {
        return action2;
    }

    public int getBlockType() {
        return blockType;
    }
}
