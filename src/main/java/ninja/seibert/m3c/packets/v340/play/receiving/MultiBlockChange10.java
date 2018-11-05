package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class MultiBlockChange10 extends ReceivingPacket {
    private int chunkX;
    private int chunkY;
    private Record[] blockChanges;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.chunkX = buff.getInt();
        this.chunkY = buff.getInt();
        int size = readVarInt(buff);
        this.blockChanges = new Record[size];
        for (int i = 0; i < size; i++) {
            byte horizontal = buff.get();
            byte x = (byte) (horizontal & 0xF0);
            byte z = (byte) (horizontal & 0x0F);
            byte y = buff.get();
            int blockStateId = readVarInt(buff);
            this.blockChanges[i] = new Record(x, y, z, blockStateId);
        }
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }

    public Record[] getBlockChanges() {
        return blockChanges;
    }

    class Record {
        private byte x;
        private byte y;
        private byte z;
        private int blockStateId;

        public Record(byte x, byte y, byte z, int blockStateId) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.blockStateId = blockStateId;
        }

        public byte getX() {
            return x;
        }

        public byte getY() {
            return y;
        }

        public byte getZ() {
            return z;
        }

        public int getBlockStateId() {
            return blockStateId;
        }
    }
}
