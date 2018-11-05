package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class UpdateScore45 extends ReceivingPacket {
    private String scoreName;
    private byte action;
    private String objectiveName;
    private Integer value;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.scoreName = readString(buff);
        this.action = buff.get();
        this.objectiveName = readString(buff);
        if (this.action != 1) {
            this.value = readVarInt(buff);
        }
    }

    public String getScoreName() {
        return scoreName;
    }

    public byte getAction() {
        return action;
    }

    public String getObjectiveName() {
        return objectiveName;
    }

    public Integer getValue() {
        return value;
    }

}
