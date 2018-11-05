package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class AttachEntity3D extends ReceivingPacket {
    private int attachedEntityId;
    private int holdingEntityId;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.attachedEntityId = buff.getInt();
        this.holdingEntityId = buff.getInt();
    }

    public int getAttachedEntityId() {
        return attachedEntityId;
    }

    public int getHoldingEntityId() {
        return holdingEntityId;
    }

}
