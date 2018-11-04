package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class BlockChange23 extends ReceivingPacket {
    private int x;
    private int y;
    private int z;
    private int blockId;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        long val = buff.getLong();
        int[] position = Utilities.positionFromLong(val);
        x = position[0];
        y = position[1];
        z = position[2];
        blockId = readVarInt(buff);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
}
