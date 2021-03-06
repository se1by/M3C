package ninja.seibert.m3c.packets.v47.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class PluginMessage3F extends ReceivingPacket {
    private String channel;
    private byte[] data;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        channel = readString(buff);
        data = new byte[buff.remaining()];
        for (int i = 0; buff.hasRemaining(); i++) {
            data[i] = buff.get();
        }
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
