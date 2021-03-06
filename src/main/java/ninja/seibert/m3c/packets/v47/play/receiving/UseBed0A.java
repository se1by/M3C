package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class UseBed0A extends ReceivingPacket {
    private int entityId;
    private int bedX;
    private int bedY;
    private int bedZ;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        long val = buff.getLong();
        bedX = (int) (val >> 38);
        bedY = (int) ((val >> 26) & 0xFFF);
        bedZ = (int) (val << 38 >> 38);
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getBedX() {
        return bedX;
    }

    public void setBedX(int bedX) {
        this.bedX = bedX;
    }

    public int getBedY() {
        return bedY;
    }

    public void setBedY(int bedY) {
        this.bedY = bedY;
    }

    public int getBedZ() {
        return bedZ;
    }

    public void setBedZ(int bedZ) {
        this.bedZ = bedZ;
    }
}
