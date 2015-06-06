package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.sending.PluginMessage23;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class PluginMessage63 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        String channel = readString(buff);
        byte[] content = bytesFromBuff(buff);
        Client.getLogger().fine("got a msg from channel " + channel);
        handleContent(channel, content, handler);
    }

    private void handleContent(String channel, byte[] content, ServerHandler handler) {
        if (channel.equals("MC|Brand")) {
            String implementation = new String(content, Charset.forName("UTF-8"));
            Client.getLogger().fine("Server is running " + implementation);
            PluginMessage23 msg = new PluginMessage23();
            msg.setChannel("MC|Brand");
            try {
                msg.setContent("M3C".getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            msg.create();
            handler.addPacketToSend(msg);
        }
    }
}
