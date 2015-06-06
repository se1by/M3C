package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class SoundEffect41 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        String name = readString(buff);
        int x = buff.getInt() / 8;
        int y = buff.getInt() / 8;
        int z = buff.getInt() / 8;
        float volume = buff.getFloat();
        int pitch = buff.get() & 0xFF;
        Client.getLogger().fine("Playing soundeffect \"" + name + "\" at " + x + "/" + y + "/" + z
                + " with vol:" + volume + " and pitch:" + pitch);
    }
}
