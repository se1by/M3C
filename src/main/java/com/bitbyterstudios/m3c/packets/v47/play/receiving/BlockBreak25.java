package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;
import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class BlockBreak25 extends ReceivingPacket {
    private int entityId;
    private int x;
    private int y;
    private int z;
    private byte destroyStage;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = readVarInt(buff);
        int[] position = Utilities.positionFromLong(buff.getLong());
        x = position[0];
        y = position[1];
        z = position[2];
        destroyStage = buff.get();
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public byte getDestroyStage() {
        return destroyStage;
    }

    public void setDestroyStage(byte destroyStage) {
        this.destroyStage = destroyStage;
    }
}
