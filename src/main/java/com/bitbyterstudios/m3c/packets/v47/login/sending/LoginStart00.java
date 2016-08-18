package com.bitbyterstudios.m3c.packets.v47.login.sending;

import com.bitbyterstudios.m3c.packets.SendingPacket;
import com.bitbyterstudios.m3c.util.Utilities;

import java.nio.ByteBuffer;

public class LoginStart00 extends SendingPacket {
    private String user;

    public LoginStart00(String user) {
        this.user = user;
    }

    @Override
    public ByteBuffer getBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(Utilities.getVarIntWidth(user.length()) + user.getBytes().length);
        writeVarInt(buffer, user.length());
        buffer.put(user.getBytes());
        return buffer;
    }

    @Override
    public int getType() {
        return 0x00;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
