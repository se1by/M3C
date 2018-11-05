package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;
import java.util.UUID;

public class SpawnPainting04 extends ReceivingPacket {
    private int entityId;
    private UUID entityUuid;
    private String title;
    private int x;
    private int y;
    private int z;
    private byte direction;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        entityUuid = readUUID(buff);
        title = readString(buff);
        long val = buff.getLong();
        x = (int) (val >> 38);
        y = (int) ((val >> 26) & 0xFFF);
        z = (int) (val << 38 >> 38);
        direction = buff.get();
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getEntityUuid() {
        return entityUuid;
    }

    public String getTitle() {
        return title;
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

    public byte getDirection() {
        return direction;
    }

}
