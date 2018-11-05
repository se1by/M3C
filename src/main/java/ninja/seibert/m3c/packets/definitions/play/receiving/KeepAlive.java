package ninja.seibert.m3c.packets.definitions.play.receiving;

import ninja.seibert.m3c.packets.ReceivingPacket;

public abstract class KeepAlive extends ReceivingPacket {
    public abstract long getId();
}
