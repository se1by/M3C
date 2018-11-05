package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class ScoreboardObjective42 extends ReceivingPacket {
    private String objectiveName;
    private byte mode;
    private String objectiveValue;
    private String type;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.objectiveName = readString(buff);
        this.mode = buff.get();
        if (this.mode == 0 || this.mode == 2) {
            this.objectiveValue = readString(buff);
            this.type = readString(buff);
        }
    }

    public String getObjectiveName() {
        return objectiveName;
    }

    public byte getMode() {
        return mode;
    }

    public String getObjectiveValue() {
        return objectiveValue;
    }

    public String getType() {
        return type;
    }

}
