package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class ScoreboardObjective3B extends ReceivingPacket {
    private String objectiveName;
    private byte mode;
    private String objectiveValue;
    private String type;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        objectiveName = readString(buff);
        mode = buff.get();
        if (buff.hasRemaining()) {
            objectiveValue = readString(buff);
            type = readString(buff);
        }
    }

    public String getObjectiveName() {
        return objectiveName;
    }

    public void setObjectiveName(String objectiveName) {
        this.objectiveName = objectiveName;
    }

    public byte getMode() {
        return mode;
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }

    public String getObjectiveValue() {
        return objectiveValue;
    }

    public void setObjectiveValue(String objectiveValue) {
        this.objectiveValue = objectiveValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
