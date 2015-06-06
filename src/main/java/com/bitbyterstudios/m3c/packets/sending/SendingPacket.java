package com.bitbyterstudios.m3c.packets.sending;

import com.bitbyterstudios.m3c.util.Utilities;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

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
        testdecode(buff.toByteArray());
    }

    private void testdecode(byte[] bytes) {
        if (this instanceof PlayerPositionLook06) {
            PlayerPositionLook06 ppl = ((PlayerPositionLook06) this);
            System.out.println("Should be: ");
            System.out.println("packetID: 6");
            System.out.println("x: " + ppl.getX());
            System.out.println("y: " + ppl.getY());
            System.out.println("z: " + ppl.getZ());
            System.out.println("yaw: " + ppl.getYaw());
            System.out.println("pitch: " + ppl.getPitch());
            System.out.println("on ground: " + ppl.isOnGround());

            ByteBuffer buff = ByteBuffer.wrap(bytes);

            int pLength = Utilities.readVarInt(buff);
            int length = Utilities.readVarInt(buff);

            int packetID = 0;
            double x = 0;
            double y = 0;
            double z = 0;
            float yaw = 0;
            float pitch = 0;
            boolean onGround = false;
            if (length == 0) {
                System.out.println("NOT COMPRESSED");
                packetID = Utilities.readVarInt(buff);
                x = buff.getDouble();
                y = buff.getDouble();
                z = buff.getDouble();
                yaw = buff.getFloat();
                pitch = buff.getFloat();
                onGround = buff.get() == 0x01;
            }
            System.out.println(pLength + "|" + length + "|" + packetID + "|" + x + "|" + y + "|" + z + "|" + yaw + "|"
                    + pitch + "|" + onGround);
            while (buff.position() < buff.limit())
                System.out.println(buff.get());
        }
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
