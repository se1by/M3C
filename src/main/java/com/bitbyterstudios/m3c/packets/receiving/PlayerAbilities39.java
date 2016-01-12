package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class PlayerAbilities39 extends ReceivingPacket {
    private byte flags;
    private float flyingSpeed;
    private float walkingSpeed;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
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
