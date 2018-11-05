package ninja.seibert.m3c.packets.definitions.play.receiving;

import ninja.seibert.m3c.packets.ReceivingPacket;

public abstract class Disconnect extends ReceivingPacket {
    public abstract String getReason();
}
