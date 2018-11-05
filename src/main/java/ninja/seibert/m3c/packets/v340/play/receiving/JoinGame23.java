package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class JoinGame23 extends ReceivingPacket {
    private int entityId;
    private short gamemode;
    private int dimension;
    private short difficulty;
    private short maxPlayers; // Don't trust this value
    private String levelType;
    private boolean reducedDebug;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = buff.getInt();
        this.gamemode = (short) (buff.get() & 0xFF);
        this.dimension = buff.getInt();
        this.difficulty = (short) (buff.get() & 0xFF);
        this.maxPlayers = (short) (buff.get() & 0xFF);
        this.levelType = readString(buff);
        this.reducedDebug = buff.get() == 1;
    }

    public int getEntityId() {
        return entityId;
    }

    public short getGamemode() {
        return gamemode;
    }

    public int getDimension() {
        return dimension;
    }

    public short getDifficulty() {
        return difficulty;
    }

    public short getMaxPlayers() {
        return maxPlayers;
    }

    public String getLevelType() {
        return levelType;
    }

    public boolean isReducedDebug() {
        return reducedDebug;
    }

    }
