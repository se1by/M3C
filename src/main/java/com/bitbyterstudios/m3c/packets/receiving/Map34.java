package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Map34 extends ReceivingPacket {
    private int damage;
    private byte scale;
    private byte[] mapData;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        damage = readVarInt(buff);
        scale = buff.get();
        mapData = new byte[buff.remaining()];
        for (int i = 0; buff.hasRemaining(); i++) {
            mapData[i] = buff.get();
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public byte getScale() {
        return scale;
    }

    public void setScale(byte scale) {
        this.scale = scale;
    }

    public byte[] getMapData() {
        return mapData;
    }

    public void setMapData(byte[] mapData) {
        this.mapData = mapData;
    }
}
