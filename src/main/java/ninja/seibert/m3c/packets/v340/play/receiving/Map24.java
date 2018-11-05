package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.nbt.AbstractTag;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Map24 extends ReceivingPacket {
    private int mapId;
    private byte scale;
    private boolean trackingPosition;
    private Icon[] icons;
    private byte updatedColumns;
    private byte updatedRows;
    private byte xOffset;
    private byte zOffset;
    private AbstractTag mapData;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        mapId = readVarInt(buff);
        scale = buff.get();
        trackingPosition = buff.get() == 1;

        icons = new Icon[readVarInt(buff)];
        for (int i = 0; i < icons.length; i++) {
            byte directionAndType = buff.get();
            icons[i] = new Icon((byte) (directionAndType & 0xF0), (byte) (directionAndType & 0x0F), buff.get(), buff.get());
        }

        updatedColumns = buff.get();
        if (updatedColumns > 1) return;

        updatedRows = buff.get();
        xOffset = buff.get();
        zOffset = buff.get();

        byte[] rawMapData = new byte[readVarInt(buff)];
        for (int i = 0; i < rawMapData.length; i++) {
            rawMapData[i] = buff.get();
        }

        // Highly experimental & untested
        //try {
            //rawMapData = Utilities.decompress(rawMapData);
//                    mapData = AbstractTag.fromByteBuffer(ByteBuffer.wrap(rawMapData), true);
//        } catch (IOException | DataFormatException e) {
//            e.printStackTrace();
//        }
    }

    public int getMapId() {
        return mapId;
    }


    public byte getScale() {
        return scale;
    }

    public boolean isTrackingPosition() {
        return trackingPosition;
    }

    public Icon[] getIcons() {
        return icons;
    }

    public byte getUpdatedColumns() {
        return updatedColumns;
    }

    public byte getUpdatedRows() {
        return updatedRows;
    }

    public byte getxOffset() {
        return xOffset;
    }

    public byte getzOffset() {
        return zOffset;
    }

    public AbstractTag getMapData() {
        return mapData;
    }


    class Icon {
        private byte type;
        private byte direction;
        private byte x;
        private byte z;

        public Icon(byte type, byte direction, byte x, byte z) {
            this.type = type;
            this.direction = direction;
            this.x = x;
            this.z = z;
        }

        public byte getType() {
            return type;
        }

        public byte getDirection() {
            return direction;
        }

        public byte getX() {
            return x;
        }

        public byte getZ() {
            return z;
        }
    }
}
