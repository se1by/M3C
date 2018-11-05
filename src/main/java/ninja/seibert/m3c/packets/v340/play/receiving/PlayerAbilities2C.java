package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class PlayerAbilities2C extends ReceivingPacket {
    private byte flags;
    private float flyingSpeed;
    private float walkingSpeed;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.flags = buff.get();
        this.flyingSpeed = buff.getFloat();
        this.walkingSpeed = buff.getFloat();
    }

    public byte getFlags() {
        return flags;
    }

    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public float getWalkingSpeed() {
        return walkingSpeed;
    }

}
