package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class NamedSoundEffect19 extends ReceivingPacket {
    private String soundName;
    private int soundCategory;
    private int effectX;
    private int effectY;
    private int effectZ;
    private float volume;
    private float pitch;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.soundName = readString(buff);
        this.soundCategory = readVarInt(buff);
        this.effectX = buff.getInt();
        this.effectY = buff.getInt();
        this.effectZ = buff.getInt();
        this.volume = buff.getFloat();
        this.pitch = buff.getFloat();
    }

    public String getSoundName() {
        return soundName;
    }

    public int getSoundCategory() {
        return soundCategory;
    }

    public int getEffectX() {
        return effectX;
    }

    public int getEffectY() {
        return effectY;
    }

    public int getEffectZ() {
        return effectZ;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    }
