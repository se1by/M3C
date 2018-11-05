package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class EntityRelativeMove26 extends ReceivingPacket {
    private int entityId;
    private float deltaX;
    private float deltaY;
    private float deltaZ;
    private boolean onGround;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.deltaX = buff.getShort();
        this.deltaY = buff.getShort();
        this.deltaZ = buff.getShort();
        this.onGround = buff.get() == 1;
    }

    public int getEntityId() {
        return entityId;
    }


    public float getDeltaX() {
        return deltaX;
    }


    public float getDeltaY() {
        return deltaY;
    }


    public float getDeltaZ() {
        return deltaZ;
    }

    public boolean isOnGround() {
        return onGround;
    }

}
