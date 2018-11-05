package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Respawn35 extends ReceivingPacket {
    private int dimension;
    private short difficulty;
    private short gamemode;
    private String levelType;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.dimension = buff.getInt();
        this.difficulty = (short) (buff.get() & 0xFF);
        this.gamemode = (short) (buff.get() & 0xFF);
        this.levelType = readString(buff);
    }

    public int getDimension() {
        return dimension;
    }

    public short getDifficulty() {
        return difficulty;
    }

    public short getGamemode() {
        return gamemode;
    }

    public String getLevelType() {
        return levelType;
    }

}
