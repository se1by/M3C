package ninja.seibert.m3c;

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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("agent", AGENT_OBJECT);
        jsonObject.put("username", user);
        jsonObject.put("password", pass);

        JSONObject parsedResult = sendJson(jsonObject, AUTH_URI, AUTH_END);
        if (parsedResult == null) {
            return null;
        }

        ClientData data = new ClientData();
        data.setAccessToken(parsedResult.get("accessToken").toString());
        data.setClientToken(parsedResult.get("clientToken").toString());
        data.setUser(((JSONObject) parsedResult.get("selectedProfile")).get("name").toString());
        data.setUuid(((JSONObject) parsedResult.get("selectedProfile")).get("id").toString());
        return data;
    }

    public static void invalidate(ClientData data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clientToken", data.getClientToken());
        jsonObject.put("accessToken", data.getAccessToken());
        sendJson(jsonObject, AUTH_URI, INVALIDATE_END);
    }

    public static void sendSessionRequest(String accessToken, String serverId, String uuid) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", accessToken);
        jsonObject.put("serverId", serverId);
        jsonObject.put("selectedProfile", uuid);
        sendJson(jsonObject, SESSION_URI, JOIN_END);
    }

    private static JSONObject sendJson(JSONObject jsonObject, String uri, String endpoint) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(uri + endpoint);

        try {
            StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-Type", "application/json");
            response = client.execute(httpPost);
            if (response.getEntity() == null) { //Empty response = went well
                return jsonObject;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String rawJson = br.readLine();
            JSONObject parsedObject = (JSONObject) parser.parse(rawJson);
            if (parsedObject.containsKey("error")) {
                Client.getLogger().warning(parsedObject.toJSONString());
            }
            return parsedObject;
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        } finally {
            try {
                client.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return null;
    }
}
