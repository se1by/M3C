package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class Statistics37 extends ReceivingPacket {
    private String[] names;
    private int[] values;

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        int size = readVarInt(buff);
        names = new String[size];
        values = new int[size];
        for (int i = 0; i < size; i++) {
            names[i] = readString(buff);
            values[i] = readVarInt(buff);
        }
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }
}
