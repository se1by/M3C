package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SetPassengers43 extends ReceivingPacket {
    private int vehicleEntityId;
    private int[] passengerEntityIds;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.vehicleEntityId = readVarInt(buff);
        this.passengerEntityIds = new int[readVarInt(buff)];

        for (int i = 0; i < this.passengerEntityIds.length; i++) {
            this.passengerEntityIds[i] = readVarInt(buff);
        }
    }

    public int getVehicleEntityId() {
        return vehicleEntityId;
    }

    public int[] getPassengerEntityIds() {
        return passengerEntityIds;
    }
}
