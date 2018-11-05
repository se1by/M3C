package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Animation06 extends ReceivingPacket {
    private int entityId;
    private byte animation;
    // 0	Swing arm
    // 1	Take damage
    // 2	Leave bed
    // 3	Eat food
    // 4	Critical effect
    // 5	Magic critical effect

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        entityId = readVarInt(buff);
        animation = (byte) (buff.get() & 0xFF);
    }

    public int getEntityId() {
        return entityId;
    }

    public byte getAnimation() {
        return animation;
    }

}
