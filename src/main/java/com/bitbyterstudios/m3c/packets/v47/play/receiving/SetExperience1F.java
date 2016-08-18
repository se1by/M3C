package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SetExperience1F extends ReceivingPacket {
    private float experiencebar;
    private int level;
    private int totalExperience;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        experiencebar = buff.getFloat();
        level = readVarInt(buff);
        totalExperience = readVarInt(buff);
    }

    public float getExperiencebar() {
        return experiencebar;
    }

    public void setExperiencebar(float experiencebar) {
        this.experiencebar = experiencebar;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }
}
