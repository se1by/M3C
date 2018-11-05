package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class EntityLookAndRelativeMove27 extends ReceivingPacket {
    private int entityId;
    private short deltaX;
    private short deltaY;
    private short deltaZ;
    private byte yaw;
    private byte pitch;
    private boolean onGround;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.deltaX = buff.getShort();
        this.deltaY = buff.getShort();
        this.deltaZ = buff.getShort();
        this.yaw = buff.get();
        this.pitch = buff.get();
        this.onGround = buff.get() == 1;
    }

    public int getEntityId() {
        return entityId;
    }

    public short getDeltaX() {
        return deltaX;
    }

    public short getDeltaY() {
        return deltaY;
    }

    public short getDeltaZ() {
        return deltaZ;
    }

    public byte getYaw() {
        return yaw;
    }

    public byte getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }
    
}
