package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class EntityEffect4F extends ReceivingPacket {
    private int entityId;
    private byte effectId;
    private byte amplifier;
    private int duration;
    private byte flags;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.effectId = buff.get();
        this.amplifier = buff.get();
        this.duration = readVarInt(buff);
        this.flags = buff.get();
    }

    public int getEntityId() {
        return entityId;
    }

    public byte getEffectId() {
        return effectId;
    }

    public byte getAmplifier() {
        return amplifier;
    }

    public int getDuration() {
        return duration;
    }

    public byte getFlags() {
        return flags;
    }
}
