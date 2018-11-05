package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class EntityLook28 extends ReceivingPacket {
    private int entityId;
    private byte yaw;
    private byte pitch;
    private boolean onGround;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        yaw = buff.get();
        pitch = buff.get();
        onGround = buff.get() == 1;
    }

    public int getEntityId() {
        return entityId;
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
