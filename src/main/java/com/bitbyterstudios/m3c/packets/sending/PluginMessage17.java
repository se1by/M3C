package com.bitbyterstudios.m3c.packets.sending;

public class PluginMessage17 extends SendingPacket {
    private static final int PACKET_ID = 17;
    private String channel;
    private byte[] content;

    @Override
    public void create() {
        writeVarInt(PACKET_ID);
        writeString(channel);
        writeBytes(content);
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
