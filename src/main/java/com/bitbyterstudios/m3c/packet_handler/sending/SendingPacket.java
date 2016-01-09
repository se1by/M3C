package com.bitbyterstudios.m3c.packet_handler.sending;

import com.bitbyterstudios.m3c.util.Utilities;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class SendingPacket {
    protected ByteArrayDataOutput buff;

    protected SendingPacket() {
        this.buff = ByteStreams.newDataOutput();
    }

    public void writeString(String s) {
        writeVarInt(s.length());
        buff.write(s.getBytes());
    }

    public abstract void create();

    public void send(OutputStream out, int compressionThreshold) throws IOException {
        ByteArrayDataOutput buff2 = ByteStreams.newDataOutput();

        if (compressionThreshold > 0) {
            if (buff.toByteArray().length > compressionThreshold) {
                Utilities.writeVarInt(buff2, buff.toByteArray().length);
                buff2.write(Utilities.compress(buff.toByteArray()));
            } else {
                buff2.write(0);
                buff2.write(buff.toByteArray());
            }
        } else {
            buff2.write(buff.toByteArray());
        }

        buff = ByteStreams.newDataOutput();
        writeVarInt(buff2.toByteArray().length);
        buff.write(buff2.toByteArray());
        out.write(buff.toByteArray());
        out.flush();
    }

    public void writeBytes(byte[] data) {
        writeVarInt(data.length);
        buff.write(data);
    }

    public int readVarInt(DataInputStream ins) throws IOException{
        int i = 0;
        int j = 0;
        while (true){
            int k = ins.read();

            i |= (k & 0x7F) << j++ * 7;

            if (j > 5) throw new RuntimeException("VarInt too big");

            if ((k & 0x80) != 128) break;
        }

        return i;
    }

    public void writeVarInt(int paramInt) {
        while (true) {
            if ((paramInt & 0xFFFFFF80) == 0) {
                buff.writeByte((byte) paramInt);
                return;
            }

            buff.writeByte((byte) (paramInt & 0x7F | 0x80));
            paramInt >>>= 7;
        }
    }
}