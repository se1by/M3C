package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class CombatEvent2D extends ReceivingPacket {
    private int event; // 0: enter combat, 1: end combat, 2: entity dead
    private Integer duration;
    private Integer playerId;
    private Integer entityId;
    private String message;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.event = readVarInt(buff);
        if (this.event == 1) {
            this.duration = readVarInt(buff);
            this.entityId = buff.getInt();
        } else if (this.event == 2) {
            this.playerId = readVarInt(buff);
            this.entityId = buff.getInt();
            this.message = readString(buff);
        }
    }

    public int getEvent() {
        return event;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public String getMessage() {
        return message;
    }

}
