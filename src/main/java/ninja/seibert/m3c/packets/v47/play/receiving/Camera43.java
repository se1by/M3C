package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Camera43 extends ReceivingPacket {
    private int cameraId;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        cameraId = readVarInt(buff);
    }
}
