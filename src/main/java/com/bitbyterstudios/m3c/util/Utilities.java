package com.bitbyterstudios.m3c.util;

public class Utilities {

    public static byte getBit(byte toCheck, int position) {
        return (byte) ((toCheck >> position) & 1);
    }

}
