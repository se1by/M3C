package com.bitbyterstudios.m3c.packet_handler.receiving;

import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class PlayerAbilities57 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        byte flags = buff.get();
        boolean creative = Utilities.getBit(flags, 0);
        boolean flying = Utilities.getBit(flags, 1);
        boolean godmode = Utilities.getBit(flags, 2);
        boolean nodamage = Utilities.getBit(flags, 3);
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
