package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Teams3E extends ReceivingPacket {
    private String teamName;
    private byte mode;
    // Optional values below
    private String teamDisplayName;
    private String teamPrefix;
    private String teamSuffix;
    private byte friendlyFire;
    private String nametagVisibility;
    private byte color;
    private String[] players;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        teamName = readString(buff);
        mode = buff.get();
        if (mode == 0 || mode == 2) {
            teamDisplayName = readString(buff);
            teamPrefix = readString(buff);
            teamSuffix = readString(buff);
            friendlyFire = buff.get();
            nametagVisibility = readString(buff);
            color = buff.get();
        }
        if (mode == 0 || mode == 3 || mode == 4) {
            players = new String[readVarInt(buff)];
            for (int i = 0; i < players.length; i++) {
                players[i] = readString(buff);
            }
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public byte getMode() {
        return mode;
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }

    public String getTeamDisplayName() {
        return teamDisplayName;
    }

    public void setTeamDisplayName(String teamDisplayName) {
        this.teamDisplayName = teamDisplayName;
    }

    public String getTeamPrefix() {
        return teamPrefix;
    }

    public void setTeamPrefix(String teamPrefix) {
        this.teamPrefix = teamPrefix;
    }

    public String getTeamSuffix() {
        return teamSuffix;
    }

    public void setTeamSuffix(String teamSuffix) {
        this.teamSuffix = teamSuffix;
    }

    public byte getFriendlyFire() {
        return friendlyFire;
    }

    public void setFriendlyFire(byte friendlyFire) {
        this.friendlyFire = friendlyFire;
    }

    public String getNametagVisibility() {
        return nametagVisibility;
    }

    public void setNametagVisibility(String nametagVisibility) {
        this.nametagVisibility = nametagVisibility;
    }

    public byte getColor() {
        return color;
    }

    public void setColor(byte color) {
        this.color = color;
    }

    public String[] getPlayers() {
        return players;
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }
}
