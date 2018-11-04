package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.nbt.AbstractTag;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class UpdateEntityNbt49 extends ReceivingPacket {
    private int entityId;
    private AbstractTag nbtTag;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        nbtTag = AbstractTag.fromByteBuffer(buff, true);
        System.out.println(nbtTag.getName());
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public AbstractTag getNbtTag() {
        return nbtTag;
    }

    public void setNbtTag(AbstractTag nbtTag) {
        this.nbtTag = nbtTag;
    }
}
