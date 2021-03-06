package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.EntityMetadata;

import java.nio.ByteBuffer;

public class EntityMetadata1C extends ReceivingPacket {
    private int entityId;
    private EntityMetadata metadata;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        metadata = EntityMetadata.fromByteBuffer(buff);
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public EntityMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(EntityMetadata metadata) {
        this.metadata = metadata;
    }
}
