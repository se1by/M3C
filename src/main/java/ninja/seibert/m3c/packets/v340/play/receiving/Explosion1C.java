package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Explosion1C extends ReceivingPacket {
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
        this.x = buff.getFloat();
        this.y = buff.getFloat();
        this.z = buff.getFloat();
        this.radius = buff.getFloat();
        this.records = new byte[buff.getInt()][3];
        for (int i = 0; i < this.records.length; i++) {
            this.records[i][0] = buff.get();
            this.records[i][1] = buff.get();
            this.records[i][2] = buff.get();
        }
        this.playerMotionX = buff.getFloat();
        this.playerMotionY = buff.getFloat();
        this.playerMotionZ = buff.getFloat();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getRadius() {
        return radius;
    }

    public byte[][] getRecords() {
        return records;
    }

    public float getPlayerMotionX() {
        return playerMotionX;
    }

    public float getPlayerMotionY() {
        return playerMotionY;
    }

    public float getPlayerMotionZ() {
        return playerMotionZ;
    }

}
