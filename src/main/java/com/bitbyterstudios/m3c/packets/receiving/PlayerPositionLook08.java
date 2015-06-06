package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.sending.PlayerPositionLook06;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class PlayerPositionLook08 extends ReceivingPacket {
    private double x, y, z;
    private float yaw, pitch;
    private boolean onGround;

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            x = in.readDouble();
            y = in.readDouble();
            z = in.readDouble();
            yaw = in.readFloat();
            pitch = in.readFloat();
            onGround = in.readBoolean();

            handler.getData().getLocation().updateFromPlayerPositionLook08(this);
            PlayerPositionLook06 positionLook06 = new PlayerPositionLook06();
            positionLook06.setPosition(x, y, z, yaw, pitch, onGround);
            positionLook06.create();
            handler.addPacketToSend(positionLook06);
            handler.getData().setSpawned(true);

            Client.getLogger().fine("Position: x=" + x + ", y=" + y +", z=" + z
                    + ", yaw=" + yaw + ", pitch=" + pitch + ", onGround=" + onGround);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        x = buff.getDouble();
        y = buff.getDouble();
        z = buff.getDouble();
        yaw = buff.getFloat();
        pitch = buff.getFloat();
        onGround = buff.get() != 0;

        handler.getData().getLocation().updateFromPlayerPositionLook08(this);
        PlayerPositionLook06 positionLook06 = new PlayerPositionLook06();
        positionLook06.setPosition(x, y, z, yaw, pitch, onGround);
        positionLook06.create();
        handler.addPacketToSend(positionLook06);
        handler.getData().setSpawned(true);

        Client.getLogger().fine("Position: x=" + x + ", y=" + y + ", z=" + z
                + ", yaw=" + yaw + ", pitch=" + pitch + ", onGround=" + onGround);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }
}
