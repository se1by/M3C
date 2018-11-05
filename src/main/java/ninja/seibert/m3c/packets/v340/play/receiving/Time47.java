package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Time47 extends ReceivingPacket {
    private long worldAge;
    private long timeOfDay;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.worldAge = buff.getLong();
        this.timeOfDay = buff.getLong();
    }

    public long getWorldAge() {
        return worldAge;
    }

    public long getTimeOfDay() {
        return timeOfDay;
    }

}
