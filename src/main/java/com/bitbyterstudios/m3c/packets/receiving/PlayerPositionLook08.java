package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.sending.PlayerPositionLook06;

import java.io.DataInputStream;
import java.io.IOException;

public class PlayerPositionLook08 extends ReceivingPacket {

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            double x = in.readDouble();
            double y = in.readDouble();
            double z = in.readDouble();
            float yaw = in.readFloat();
            float pitch = in.readFloat();
            boolean onGround = in.readBoolean();

            if (!handler.getData().isSpawned()) {
                PlayerPositionLook06 positionLook06 = new PlayerPositionLook06();
                positionLook06.setPosition(x, y, z, yaw, pitch, onGround);
                positionLook06.create();
                handler.addPacketToSend(positionLook06);
                handler.getData().setSpawned(true);
            }
            Client.getLogger().fine("Position: x=" + x + ", y=" + y +", z=" + z
                    + ", yaw=" + yaw + ", pitch=" + pitch + ", onGround=" + onGround);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
