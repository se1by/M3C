package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.sending.PluginMessage17;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class PluginMessage63 extends ReceivingPacket {

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            String channel = readString(in);
            byte[] content = bytesFromStream(in);
            Client.getLogger().fine("got a msg from channel " + channel);
            handleContent(channel, content, handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleContent(String channel, byte[] content, ServerHandler handler) {
        if (channel.equals("MC|Brand")) {
            String implementation = new String(content, Charset.forName("UTF-8"));
            Client.getLogger().fine("Server is running " + implementation);
            PluginMessage17 msg = new PluginMessage17();
            msg.setChannel("MC|Brand");
            try {
                msg.setContent("M3C".getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            msg.create();
            //handler.addPacketToSend(msg);
        }
    }
}
