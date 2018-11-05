package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Camera39 extends ReceivingPacket {
    private int cameraId;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.cameraId = readVarInt(buff);
    }

    public int getCameraId() {
        return cameraId;
    }
}
