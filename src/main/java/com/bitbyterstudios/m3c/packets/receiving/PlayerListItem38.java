package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class PlayerListItem38 extends ReceivingPacket {
    private int action;
    private byte[] itemData;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        action = readVarInt(buff);
        itemData = new byte[buff.remaining()];
        for (int i = 0; buff.hasRemaining(); i++) {
            itemData[i] = buff.get();
        }
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public byte[] getItemData() {
        return itemData;
    }

    public void setItemData(byte[] itemData) {
        this.itemData = itemData;
    }
}
