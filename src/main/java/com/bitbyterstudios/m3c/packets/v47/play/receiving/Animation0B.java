package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Animation0B extends ReceivingPacket {
    private int entityId;
    private byte animation;
    // 0	Swing arm
    // 1	Take damage
    // 2	Leave bed
    // 3	Eat food
    // 4	Critical effect
    // 5	Magic critical effect

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        animation = (byte) (buff.get() & 0xFF);
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public byte getAnimation() {
        return animation;
    }

    public void setAnimation(byte animation) {
        this.animation = animation;
    }
}
