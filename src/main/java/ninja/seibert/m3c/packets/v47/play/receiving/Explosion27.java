package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Explosion27 extends ReceivingPacket {
    private float x;
    private float y;
    private float z;
    private float radius;
    private byte[][] records;
    private float playerMotionX;
    private float playerMotionY;
    private float playerMotionZ;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        x = buff.getFloat();
        y = buff.getFloat();
        z = buff.getFloat();
        radius = buff.getFloat();
        records = new byte[buff.getInt()][3];
        for (int i = 0; i < records.length; i++) {
            records[i][0] = buff.get();
            records[i][1] = buff.get();
            records[i][2] = buff.get();
        }
        playerMotionX = buff.getFloat();
        playerMotionY = buff.getFloat();
        playerMotionZ = buff.getFloat();
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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public byte[][] getRecords() {
        return records;
    }

    public void setRecords(byte[][] records) {
        this.records = records;
    }

    public float getPlayerMotionX() {
        return playerMotionX;
    }

    public void setPlayerMotionX(float playerMotionX) {
        this.playerMotionX = playerMotionX;
    }

    public float getPlayerMotionY() {
        return playerMotionY;
    }

    public void setPlayerMotionY(float playerMotionY) {
        this.playerMotionY = playerMotionY;
    }

    public float getPlayerMotionZ() {
        return playerMotionZ;
    }

    public void setPlayerMotionZ(float playerMotionZ) {
        this.playerMotionZ = playerMotionZ;
    }
}
