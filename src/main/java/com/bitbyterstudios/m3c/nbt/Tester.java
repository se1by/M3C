package com.bitbyterstudios.m3c.nbt;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Tester {

    public static void main(String[] args) throws IOException {
        File file = new File("bigtest.nbt");
        if (!file.exists()) {
            System.err.println("Couldn't find file " + file.getAbsolutePath() + "!");
            return;
        }
        RandomAccessFile accessFile = new RandomAccessFile(file, "r");
        FileChannel channel = accessFile.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        AbstractTag tag = AbstractTag.fromByteBuffer(buffer, true);
        System.out.println(tag.getName());
    }
}
