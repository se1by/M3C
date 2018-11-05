package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class Statistics07 extends ReceivingPacket {
    private Map<String, Integer> statistics;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.statistics = new HashMap<>();
        int size = readVarInt(buff);
        for (int i = 0; i < size; i++) {
            this.statistics.put(readString(buff), readVarInt(buff));
        }
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }
}
