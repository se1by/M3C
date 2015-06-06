package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

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

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        try {
            String jsonString = readString(buff);
            JSONObject chat = (JSONObject) parser.parse(jsonString);
            handleChat(handler, chat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void handleChat(ServerHandler handler, JSONObject chat) {
        Client.getLogger().finest(chat.toJSONString());
        if (chat.containsKey("text") && !chat.get("text").toString().isEmpty()) {
            handler.getClient().send(chat.get("text").toString());
        } else if (chat.containsKey("extra")) {
            handler.getClient().send(handleExtra((JSONArray) chat.get("extra")));
        } else if (chat.containsKey("translate")) {
            handler.getClient().send(handleTranslate(chat));
        } else {
            Client.getLogger().info("Unhandled chat message: " + chat.toJSONString());
        }
    }

    private String handleTranslate(JSONObject chat) {
        StringBuilder sb = new StringBuilder();
        if (chat.get("translate").toString().equals("death.attack.player")) {
            JSONArray with = (JSONArray) chat.get("with");
            String victim = ((JSONObject) with.get(0)).get("text").toString();
            String killer = ((JSONObject) with.get(1)).get("text").toString();
            sb.append(String.format("%s was slain by %s!", victim, killer));
        } else {
            Client.getLogger().info("Unhandled translate: " + chat.toJSONString());
        }
        return sb.toString();
    }

    private String handleExtra(JSONArray extra) {
        StringBuilder sb = new StringBuilder();
        for (Object o : extra) {
            if (o instanceof String) {
                if (((String) o).isEmpty()) continue;
                Client.getLogger().warning("Unhandled extra: " + o.toString());
            }
            JSONObject obj = (JSONObject) o;
            if (obj.get("text").toString().isEmpty()) {
                continue;
            }
            sb.append(obj.get("text").toString());
        }
        return sb.toString();
    }
}
