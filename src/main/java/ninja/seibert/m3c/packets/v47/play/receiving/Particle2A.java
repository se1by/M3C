package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Particle2A extends ReceivingPacket {
    private int particleId;
    private boolean longDistance;
    private float x;
    private float y;
    private float z;
    private float offsetX;
    private float offsetY;
    private float offsetZ;
    private float particleData;
    private int particleCount;
    private int[] data;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        particleId = buff.getInt();
        longDistance = buff.get() == 1;
        x = buff.getFloat();
        y = buff.getFloat();
        z = buff.getFloat();
        offsetX = buff.getFloat();
        offsetY = buff.getFloat();
        offsetZ = buff.getFloat();
        particleData = buff.getFloat();
        particleCount = buff.getInt();
        data = new int[buff.remaining()/4];
        for (int i = 0; i < data.length; i++) {
            data[i] = buff.getInt();
        }
    }

    public int getParticleId() {
        return particleId;
    }

    public void setParticleId(int particleId) {
        this.particleId = particleId;
    }

    public boolean isLongDistance() {
        return longDistance;
    }

    public void setLongDistance(boolean longDistance) {
        this.longDistance = longDistance;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public float getOffsetZ() {
        return offsetZ;
    }

    public void setOffsetZ(float offsetZ) {
        this.offsetZ = offsetZ;
    }

    public float getParticleData() {
        return particleData;
    }

    public void setParticleData(float particleData) {
        this.particleData = particleData;
    }

    public int getParticleCount() {
        return particleCount;
    }

    public void setParticleCount(int particleCount) {
        this.particleCount = particleCount;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }
}
