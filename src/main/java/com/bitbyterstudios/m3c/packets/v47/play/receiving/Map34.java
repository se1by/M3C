package com.bitbyterstudios.m3c.packets.v47.play.receiving;

import com.bitbyterstudios.m3c.ConnectionHandler;
import com.bitbyterstudios.m3c.nbt.AbstractTag;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;
import com.bitbyterstudios.m3c.util.Utilities;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;

public class Map34 extends ReceivingPacket {
    private int mapId;
    private byte scale;
    private AbstractTag mapData;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        mapId = readVarInt(buff);
        scale = buff.get();
        byte[] rawMapData = new byte[buff.remaining()];
        for (int i = 0; buff.hasRemaining(); i++) {
            rawMapData[i] = buff.get();
        }

        // Highly experimental & untested
        try {
            rawMapData = Utilities.decompress(rawMapData);
            mapData = AbstractTag.fromByteBuffer(ByteBuffer.wrap(rawMapData), true);
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
        }
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public byte getScale() {
        return scale;
    }

    public void setScale(byte scale) {
        this.scale = scale;
    }

    public AbstractTag getMapData() {
        return mapData;
    }

    public void setMapData(AbstractTag mapData) {
        this.mapData = mapData;
    }
}
