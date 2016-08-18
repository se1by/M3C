package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class JoinGame01 extends ReceivingPacket {
    private int entityId;
    private byte gamemode;
    private byte dimension;
    private byte difficulty;
    private byte maxPlayers; // Don't trust this value
    private String levelType;
    private boolean reducedDebug;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = buff.getInt();
        gamemode = (byte) (buff.get() & 0xFF);
        dimension = buff.get();
        difficulty = (byte) (buff.get() & 0xFF);
        maxPlayers = (byte) (buff.get() & 0xFF);
        levelType = readString(buff);
        reducedDebug = buff.get() == 1;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public byte getGamemode() {
        return gamemode;
    }

    public void setGamemode(byte gamemode) {
        this.gamemode = gamemode;
    }

    public byte getDimension() {
        return dimension;
    }

    public void setDimension(byte dimension) {
        this.dimension = dimension;
    }

    public byte getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(byte difficulty) {
        this.difficulty = difficulty;
    }

    public byte getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(byte maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public boolean isReducedDebug() {
        return reducedDebug;
    }

    public void setReducedDebug(boolean reducedDebug) {
        this.reducedDebug = reducedDebug;
    }
}
