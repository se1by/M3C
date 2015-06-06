package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.util.Utilities;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class PlayerAbilities57 extends ReceivingPacket {

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            byte flags = in.readByte();
            boolean creative = Utilities.getBit(flags, 0) == 1;
            boolean flying = Utilities.getBit(flags, 1) == 1;
            boolean godmode = Utilities.getBit(flags, 2) == 1;
            boolean nodamage = Utilities.getBit(flags, 3) == 1;
            float flyingSpeed = in.readFloat();
            float walkingSpeed = in.readFloat();
            handler.getLogger().fine("Creative: " + creative);
            handler.getLogger().fine("Flying: " + flying);
            handler.getLogger().fine("GodMode: " + godmode);
            handler.getLogger().fine("NoDamage: " + nodamage);
            handler.getLogger().fine("FlyingSpeed: " + flyingSpeed);
            handler.getLogger().fine("WalkingSpeed: " + walkingSpeed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        byte flags = buff.get();
        boolean creative = Utilities.getBit(flags, 0) == 1;
        boolean flying = Utilities.getBit(flags, 1) == 1;
        boolean godmode = Utilities.getBit(flags, 2) == 1;
        boolean nodamage = Utilities.getBit(flags, 3) == 1;
        float flyingSpeed = buff.getFloat();
        float walkingSpeed = buff.getFloat();
        handler.getLogger().fine("Creative: " + creative);
        handler.getLogger().fine("Flying: " + flying);
        handler.getLogger().fine("GodMode: " + godmode);
        handler.getLogger().fine("NoDamage: " + nodamage);
        handler.getLogger().fine("FlyingSpeed: " + flyingSpeed);
        handler.getLogger().fine("WalkingSpeed: " + walkingSpeed);
    }
}
