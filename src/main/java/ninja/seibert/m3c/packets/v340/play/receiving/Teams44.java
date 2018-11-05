package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Teams44 extends ReceivingPacket {
    private String teamName;
    private byte mode;
    // Optional values below
    private String teamDisplayName;
    private String teamPrefix;
    private String teamSuffix;
    private byte friendlyFlags;
    private String nametagVisibility;
    private String collsionRule;
    private byte color;
    private String[] players;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.teamName = readString(buff);
        this.mode = buff.get();
        if (this.mode == 0 || this.mode == 2) {
            this.teamDisplayName = readString(buff);
            this.teamPrefix = readString(buff);
            this.teamSuffix = readString(buff);
            this.friendlyFlags = buff.get();
            this.nametagVisibility = readString(buff);
            this.collsionRule = readString(buff);
            this.color = buff.get();
        }
        if (this.mode == 0 || this.mode == 3 || this.mode == 4) {
            this.players = new String[readVarInt(buff)];
            for (int i = 0; i < this.players.length; i++) {
                this.players[i] = readString(buff);
            }
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public byte getMode() {
        return mode;
    }

    public String getTeamDisplayName() {
        return teamDisplayName;
    }

    public String getTeamPrefix() {
        return teamPrefix;
    }

    public String getTeamSuffix() {
        return teamSuffix;
    }

    public byte getFriendlyFlags() {
        return friendlyFlags;
    }

    public String getNametagVisibility() {
        return nametagVisibility;
    }

    public String getCollsionRule() {
        return collsionRule;
    }

    public byte getColor() {
        return color;
    }

    public String[] getPlayers() {
        return players;
    }

}
