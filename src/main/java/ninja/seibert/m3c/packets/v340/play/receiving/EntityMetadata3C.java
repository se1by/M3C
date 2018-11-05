package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.EntityMetadata;

import java.nio.ByteBuffer;

public class EntityMetadata3C extends ReceivingPacket {
    private int entityId;
    private EntityMetadata metadata;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.metadata = EntityMetadata.fromByteBuffer(buff);
    }

    public int getEntityId() {
        return entityId;
    }

    public EntityMetadata getMetadata() {
        return metadata;
    }

}
