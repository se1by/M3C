package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class CollectItem0D extends ReceivingPacket {
    private int collectedEntityId;
    private int collectorEntityId;
    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        collectedEntityId = readVarInt(buff);
        collectorEntityId = readVarInt(buff);
    }

    public int getCollectedEntityId() {
        return collectedEntityId;
    }

    public void setCollectedEntityId(int collectedEntityId) {
        this.collectedEntityId = collectedEntityId;
    }

    public int getCollectorEntityId() {
        return collectorEntityId;
    }

    public void setCollectorEntityId(int collectorEntityId) {
        this.collectorEntityId = collectorEntityId;
    }
}
