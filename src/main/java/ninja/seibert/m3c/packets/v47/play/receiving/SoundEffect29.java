package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SoundEffect29 extends ReceivingPacket {
    private String soundName;
    private int effectX;
    private int effectY;
    private int effectZ;
    private float volume;
    private byte pitch;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        soundName = readString(buff);
        effectX = buff.getInt();
        effectY = buff.getInt();
        effectZ = buff.getInt();
        volume = buff.getFloat();
        pitch = (byte) (buff.get() & 0xFF);
    }

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    public int getEffectX() {
        return effectX;
    }

    public void setEffectX(int effectX) {
        this.effectX = effectX;
    }

    public int getEffectY() {
        return effectY;
    }

    public void setEffectY(int effectY) {
        this.effectY = effectY;
    }

    public int getEffectZ() {
        return effectZ;
    }

    public void setEffectZ(int effectZ) {
        this.effectZ = effectZ;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public byte getPitch() {
        return pitch;
    }

    public void setPitch(byte pitch) {
        this.pitch = pitch;
    }
}
