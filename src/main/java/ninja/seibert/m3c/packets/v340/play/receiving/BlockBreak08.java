package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class BlockBreak08 extends ReceivingPacket {
    private int entityId;
    private int x;
    private int y;
    private int z;
    private byte destroyStage;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);

        int[] position = Utilities.positionFromLong(buff.getLong());
        this.x = position[0];
        this.y = position[1];
        this.z = position[2];

        this.destroyStage = buff.get();
    }

    public int getEntityId() {
        return entityId;
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

    public byte getDestroyStage() {
        return destroyStage;
    }

}
