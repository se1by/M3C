package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class AttachEntity1B extends ReceivingPacket {
    private int entityId;
    private int vehicleId;
    private boolean leash;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
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
