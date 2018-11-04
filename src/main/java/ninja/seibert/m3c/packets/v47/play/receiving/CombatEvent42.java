package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class CombatEvent42 extends ReceivingPacket {
    private int event; // 0: enter combat, 1: end combat, 2: entity dead
    private Integer duration;
    private Integer playerId;
    private Integer entityId;
    private String message;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        event = readVarInt(buff);
        if (event == 1) {
            duration = readVarInt(buff);
            entityId = readVarInt(buff);
        } else if (event == 2) {
            playerId = readVarInt(buff);
            entityId = readVarInt(buff);
            message = readString(buff);
        }
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
