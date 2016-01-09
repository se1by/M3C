package com.bitbyterstudios.m3c.plugin;

import java.nio.ByteBuffer;

public interface Listener {
    ByteBuffer handle(ByteBuffer buffer);
}
