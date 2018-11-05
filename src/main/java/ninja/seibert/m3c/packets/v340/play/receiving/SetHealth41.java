package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class SetHealth41 extends ReceivingPacket {
    private float health;
    private int food;
    private float saturation;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.health = buff.getFloat();
        this.food = readVarInt(buff);
        this.saturation = buff.getFloat();
    }

    public float getHealth() {
        return health;
    }

    public int getFood() {
        return food;
    }

    public float getSaturation() {
        return saturation;
    }

}
