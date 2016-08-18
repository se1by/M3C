package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.nbt.AbstractTag;

import java.nio.ByteBuffer;

public class UpdateEntityNbt49 extends ReceivingPacket {
    private int entityId;
    private AbstractTag nbtTag;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = readVarInt(buff);
        nbtTag = AbstractTag.fromByteBuffer(buff, true);
        System.out.println(nbtTag.getName());
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public AbstractTag getNbtTag() {
        return nbtTag;
    }

    public void setNbtTag(AbstractTag nbtTag) {
        this.nbtTag = nbtTag;
    }
}
