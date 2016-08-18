package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class UpdateScore3C extends ReceivingPacket {
    private String scoreName;
    private byte action;
    private String objectiveName;
    private Integer value;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        scoreName = readString(buff);
        action = buff.get();
        objectiveName = readString(buff);
        if (buff.hasRemaining()) {
            value = readVarInt(buff);
        }
    }

    public String getScoreName() {
        return scoreName;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    public byte getAction() {
        return action;
    }

    public void setAction(byte action) {
        this.action = action;
    }

    public String getObjectiveName() {
        return objectiveName;
    }

    public void setObjectiveName(String objectiveName) {
        this.objectiveName = objectiveName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
