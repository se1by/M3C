package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SetExperience40 extends ReceivingPacket {
    private float experiencebar;
    private int level;
    private int totalExperience;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.experiencebar = buff.getFloat();
        this.level = readVarInt(buff);
        this.totalExperience = readVarInt(buff);
    }

    public float getExperiencebar() {
        return experiencebar;
    }

    public int getLevel() {
        return level;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

}
