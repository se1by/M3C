package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Health06 extends ReceivingPacket {
    private float health;
    private int food;
    private float saturation;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        health = buff.getFloat();
        food = readVarInt(buff);
        saturation = buff.getFloat();
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public float getSaturation() {
        return saturation;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }
}
