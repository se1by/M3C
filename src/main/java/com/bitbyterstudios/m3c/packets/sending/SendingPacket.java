package com.bitbyterstudios.m3c.packets.sending;

import java.nio.ByteBuffer;

public abstract class SendingPacket {
    public abstract ByteBuffer getBuffer();

    public void writeVarInt(ByteBuffer buffer, int paramInt) {
        while (true) {
            if ((paramInt & 0xFFFFFF80) == 0) {
                buffer.put((byte) paramInt);
                buffer.flip();
                return;
            }

            buffer.put((byte) (paramInt & 0x7F | 0x80));
            paramInt >>>= 7;
        }
    }
}
