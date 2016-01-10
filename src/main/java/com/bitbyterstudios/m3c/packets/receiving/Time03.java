package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Time03 extends ReceivingPacket {
    private long worldAge;
    private long timeOfDay;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        worldAge = buff.getLong();
        timeOfDay = buff.getLong();
    }

    public long getWorldAge() {
        return worldAge;
    }

    public void setWorldAge(long worldAge) {
        this.worldAge = worldAge;
    }

    public long getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(long timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
}
