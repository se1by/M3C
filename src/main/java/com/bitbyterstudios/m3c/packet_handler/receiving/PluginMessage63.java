package com.bitbyterstudios.m3c.packet_handler.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packet_handler.sending.PluginMessage23;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class PluginMessage63 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        String channel = readString(buff);
        Client.getLogger().fine("got a msg from channel " + channel);
        if (channel.equals("MC|Brand")) {
            handleBrand(buff, handler);
        } else if (channel.equals("REGISTER")) {
            handleRegister(buff);
        } else {
            Client.getLogger().warning("Unknown channel " + channel);
        }
    }

    private void handleRegister(ByteBuffer buff) {
        List<String> list = new ArrayList<String>();
        ByteBuffer temp = ByteBuffer.allocate(16);
        while (buff.hasRemaining()) {
            byte next = buff.get();
            if (next == 0x00) {
                list.add(new String(temp.array()));
                temp.clear();
            } else {
                temp.put(next);
            }
        }
        Client.getLogger().fine("Server registered these channels: " + String.join(", ", list));
    }

    private void handleBrand(ByteBuffer buff, ServerHandler handler) {
        String implementation = readString(buff);
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
