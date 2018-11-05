package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SetCooldown17 extends ReceivingPacket {
    private int itemId;
    private int cooldownTicks;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        itemId = readVarInt(buff);
        cooldownTicks = readVarInt(buff);
    }

    public int getItemId() {
        return itemId;
    }

    public int getCooldownTicks() {
        return cooldownTicks;
    }
}
