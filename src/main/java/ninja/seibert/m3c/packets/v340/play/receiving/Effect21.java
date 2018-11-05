package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class Effect21 extends ReceivingPacket {
    private int effectId;
    private int x;
    private int y;
    private int z;
    private int data;
    private boolean disableRelativeVolume;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.effectId = buff.getInt();
        int[] position = Utilities.positionFromLong(buff.getLong());
        this.x = position[0];
        this.y = position[1];
        this.z = position[2];
        this.data = buff.getInt();
        this.disableRelativeVolume = buff.get() == 1;
    }

    public int getEffectId() {
        return effectId;
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

    public int getData() {
        return data;
    }

    public boolean isDisableRelativeVolume() {
        return disableRelativeVolume;
    }

}
