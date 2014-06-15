package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.IOException;

public class Chat02 extends ReceivingPacket {

    private JSONParser parser;

    public Chat02() {
        parser = new JSONParser();
    }

    @Override
    public void read(DataInputStream in, int len, ServerHandler handler) {
        try {
            String jsonString = readString(in);
            JSONObject chat = (JSONObject) parser.parse(jsonString);
            handleChat(handler, chat);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void handleChat(ServerHandler handler, JSONObject chat) {
        Client.getLogger().info(chat.toJSONString());
        if (chat.containsKey("text") && !chat.get("text").toString().isEmpty()) {
            handler.getClient().send(chat.get("text").toString());
        } else if (chat.containsKey("extra")) {
            JSONArray extra = (JSONArray) chat.get("extra");
            handler.getClient().send(extra.get(0).toString());
        } else {
            Client.getLogger().info("Unhandled chat message: " + chat.toJSONString());
        }
    }
}
