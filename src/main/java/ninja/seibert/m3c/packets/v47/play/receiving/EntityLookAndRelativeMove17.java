package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class EntityLookAndRelativeMove17 extends ReceivingPacket {
    private int entityId;
    private byte deltaX;
    private byte deltaY;
    private byte deltaZ;
    private byte yaw;
    private byte pitch;
    private boolean onGround;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        deltaX = buff.get();
        deltaY = buff.get();
        deltaZ = buff.get();
        yaw = buff.get();
        pitch = buff.get();
        onGround = buff.get() == 1;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public byte getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(byte deltaX) {
        this.deltaX = deltaX;
    }

    public byte getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(byte deltaY) {
        this.deltaY = deltaY;
    }

    public byte getDeltaZ() {
        return deltaZ;
    }

    public void setDeltaZ(byte deltaZ) {
        this.deltaZ = deltaZ;
    }

    public byte getYaw() {
        return yaw;
    }

    public void setYaw(byte yaw) {
        this.yaw = yaw;
    }

    public byte getPitch() {
        return pitch;
    }

    public void setPitch(byte pitch) {
        this.pitch = pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
