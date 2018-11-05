package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SpawnPosition46 extends ReceivingPacket {
    private int x; //26 bit integer
    private int y; //12 bit integer
    private int z; //26 bit integer

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        long val = buff.getLong();
        this.x = (int) (val >> 38);
        this.y = (int) ((val >> 26) & 0xFFF);
        this.z = (int) (val << 38 >> 38);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

}
