package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.nbt.AbstractTag;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class UpdateBlockEntity09 extends ReceivingPacket {
    private int x;
    private int y;
    private int z;
    private byte action;
    private AbstractTag nbtTag;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        int[] position = Utilities.positionFromLong(buff.getLong());
        this.x = position[0];
        this.y = position[1];
        this.z = position[2];
        this.action = (byte) (buff.get() & 0xFF);
        this.nbtTag = AbstractTag.fromByteBuffer(buff, true);
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

    public byte getAction() {
        return action;
    }

    public AbstractTag getNbtTag() {
        return nbtTag;
    }

}
