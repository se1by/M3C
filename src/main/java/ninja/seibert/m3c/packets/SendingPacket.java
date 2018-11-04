package ninja.seibert.m3c.packets;

import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;

public abstract class SendingPacket {
    public abstract ByteBuffer getBuffer();

    public abstract int getType();

    protected byte[] getVarint(int realInt) {
        byte[] varInt = new byte[Utilities.getVarIntWidth(realInt)];
        for (int i = 0; i < varInt.length; i++) {
            if ((realInt & 0xFFFFFF80) == 0) {
                varInt[i] = (byte) realInt;
                return varInt;
            }

            varInt[i] = (byte) (realInt & 0x7F | 0x80);
            realInt >>>= 7;
        }
        return varInt;
    }

    public void writeVarInt(ByteBuffer buffer, int paramInt) {
        buffer.put(getVarint(paramInt));
    }
}
