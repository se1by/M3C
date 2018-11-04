package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class EntityEffect1D extends ReceivingPacket {
    private int entityId;
    private byte effectId;
    private byte amplifier;
    private int duration;
    private boolean hideParticles;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        effectId = buff.get();
        amplifier = buff.get();
        duration = readVarInt(buff);
        hideParticles = buff.get() == 1;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public byte getEffectId() {
        return effectId;
    }

    public void setEffectId(byte effectId) {
        this.effectId = effectId;
    }

    public byte getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(byte amplifier) {
        this.amplifier = amplifier;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isHideParticles() {
        return hideParticles;
    }

    public void setHideParticles(boolean hideParticles) {
        this.hideParticles = hideParticles;
    }
}
