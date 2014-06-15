package com.bitbyterstudios.m3c.packets.sending;

public class PlayerPositionLook06 extends SendingPacket {
    private static final int PACKET_ID = 6;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private boolean onGround;

    public PlayerPositionLook06() {
        super();
    }

    @Override
    public void create() {
        writeVarInt(PACKET_ID);
        buff.writeDouble(x);
        buff.writeDouble(y - 1.62);
        buff.writeDouble(y);
        buff.writeDouble(z);
        buff.writeFloat(yaw);
        buff.writeFloat(pitch);
        buff.writeBoolean(onGround);
    }

    public void setPosition(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }
}
