package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class PlayerAbilities39 extends ReceivingPacket {
    private byte flags;
    private float flyingSpeed;
    private float walkingSpeed;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        flags = buff.get();
        flyingSpeed = buff.getFloat();
        walkingSpeed = buff.getFloat();
    }

    public byte getFlags() {
        return flags;
    }

    public void setFlags(byte flags) {
        this.flags = flags;
    }

    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public void setFlyingSpeed(float flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    public float getWalkingSpeed() {
        return walkingSpeed;
    }

    public void setWalkingSpeed(float walkingSpeed) {
        this.walkingSpeed = walkingSpeed;
    }
}
