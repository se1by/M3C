package com.bitbyterstudios.m3c;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ApiAccess {
    private static final String AUTH_URI = "https://authserver.mojang.com";
    private static final String AUTH_END = "/authenticate";
    private static final String INVALIDATE_END = "/invalidate";
    private static final String SESSION_URI = "https://sessionserver.mojang.com";
    private static final String JOIN_END = "/session/minecraft/join";
    private static final JSONObject AGENT_OBJECT;

    private static JSONParser parser = new JSONParser();

    static {
        AGENT_OBJECT = new JSONObject();
        AGENT_OBJECT.put("name", "Minecraft");
        AGENT_OBJECT.put("version", 1);
    }


    public static ClientData authenticate(String user, String pass) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(AUTH_URI + AUTH_END);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("agent", AGENT_OBJECT);
        jsonObject.put("username", user);
        jsonObject.put("password", pass);
        try {
            StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-Type", "application/json");
            response = client.execute(httpPost);
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String s = br.readLine();
            JSONObject parsedObject = (JSONObject) parser.parse(s);
            if (parsedObject.containsKey("error")) {
                return null;
            }
            ClientData data = new ClientData();
            data.setAccessToken(parsedObject.get("accessToken").toString());
            data.setClientToken(parsedObject.get("clientToken").toString());
            data.setUser(((JSONObject) parsedObject.get("selectedProfile")).get("name").toString());
            data.setUuid(((JSONObject) parsedObject.get("selectedProfile")).get("id").toString());
            return data;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void invalidate(ClientData data) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(AUTH_URI + INVALIDATE_END);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clientToken", data.getClientToken());
        jsonObject.put("accessToken", data.getAccessToken());
        try {
            StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-Type", "application/json");
            response = client.execute(httpPost);
            if (response.getEntity() == null) { //Empty response = went well
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String s = br.readLine();
            JSONObject parsedObject = (JSONObject) parser.parse(s);
            if (parsedObject.containsKey("error")) {
                Client.getLogger().warning(parsedObject.toJSONString());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendSessionRequest(String accessToken, String serverId, String uuid) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(SESSION_URI + JOIN_END);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", accessToken);
        jsonObject.put("serverId", serverId);
        jsonObject.put("selectedProfile", uuid);
        try {
            StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-Type", "application/json");
            response = client.execute(httpPost);
            if (response.getEntity() == null) { //Empty response = went well
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String s = br.readLine();
            JSONObject parsedObject = (JSONObject) parser.parse(s);
            if (parsedObject.containsKey("error")) {
                Client.getLogger().warning(parsedObject.toJSONString());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
