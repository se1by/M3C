package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class SoundEffect41 extends ReceivingPacket {

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            String name = readString(in);
            int x = in.readInt();
            int y = in.readInt();
            int z = in.readInt();
            float volume = in.readFloat();
            int pitch = in.readByte() & 0xFF;
            Client.getLogger().fine("Playing soundeffect \"" + name + "\" at " + x + "/" + y + "/" + z
                    + " with vol:" + volume + " and pitch:" + pitch);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        String name = readString(buff);
        int x = buff.getInt();
        int y = buff.getInt();
        int z = buff.getInt();
        float volume = buff.getFloat();
        int pitch = buff.get() & 0xFF;
        Client.getLogger().fine("Playing soundeffect \"" + name + "\" at " + x + "/" + y + "/" + z
                + " with vol:" + volume + " and pitch:" + pitch);
    }
}
