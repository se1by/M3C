package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class AttachEntity1B extends ReceivingPacket {
    private int entityId;
    private int vehicleId;
    private boolean leash;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = buff.getInt();
        vehicleId = buff.getInt();
        leash = buff.get() == 1;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public boolean isLeash() {
        return leash;
    }

    public void setLeash(boolean leash) {
        this.leash = leash;
    }
}
