package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class UseBed30 extends ReceivingPacket {
    private int entityId;
    private int bedX;
    private int bedY;
    private int bedZ;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        long val = buff.getLong();
        this.bedX = (int) (val >> 38);
        this.bedY = (int) ((val >> 26) & 0xFFF);
        this.bedZ = (int) (val << 38 >> 38);
    }

    public int getEntityId() {
        return entityId;
    }

    public int getBedX() {
        return bedX;
    }

    public int getBedY() {
        return bedY;
    }

    public int getBedZ() {
        return bedZ;
    }

}
