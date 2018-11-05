package ninja.seibert.m3c.packets.definitions.login.receiving;

import ninja.seibert.m3c.packets.ReceivingPacket;

public abstract class SetCompression extends ReceivingPacket {
    public abstract int getThreshold();
}
