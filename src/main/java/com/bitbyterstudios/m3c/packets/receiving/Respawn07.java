package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Respawn07 extends ReceivingPacket {
    private int dimension;
    private byte difficulty;
    private byte gamemode;
    private String levelType;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        dimension = buff.getInt();
        difficulty = (byte) (buff.get() & 0xFF);
        gamemode = (byte) (buff.get() & 0xFF);
        levelType = readString(buff);
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public byte getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(byte difficulty) {
        this.difficulty = difficulty;
    }

    public byte getGamemode() {
        return gamemode;
    }

    public void setGamemode(byte gamemode) {
        this.gamemode = gamemode;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }
}
