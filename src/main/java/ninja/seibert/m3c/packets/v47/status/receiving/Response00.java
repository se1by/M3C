package ninja.seibert.m3c.packets.v47.status.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.ByteBuffer;

public class Response00 extends ReceivingPacket {
    private String rawJson;

    private String versionName;
    private int protocol;

    private long maxPlayers;
    private long onlinePlayerCount;

    private String samplePlayerName;
    private String samplePlayerUUID;

    private String descriptionText;

    // base64 encoded png
    private String favIcon;


    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        String json = readString(buff);
        this.rawJson = json;

        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) new JSONParser().parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        JSONObject versionObject = ((JSONObject) jsonObject.get("version"));
        this.versionName = (String) versionObject.get("name");
        this.protocol = ((Long) versionObject.get("protocol")).intValue();

        JSONObject playersObject = (JSONObject) jsonObject.get("players");
        this.maxPlayers = (long) playersObject.get("max");
        this.onlinePlayerCount = (long) playersObject.get("online");

        if (playersObject.containsKey("sample")) {
            JSONObject playerSampleObject = (JSONObject) ((JSONArray) playersObject.get("sample")).get(0);
            this.samplePlayerName = (String) playerSampleObject.get("name");
            this.samplePlayerUUID = (String) playerSampleObject.get("id");
        }

        JSONObject descriptionObject =  (JSONObject) jsonObject.get("description");
        this.descriptionText = (String) descriptionObject.get("text");

        this.favIcon = (String) jsonObject.get("favicon");
    }


    public String getRawJson() {
        return rawJson;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getProtocol() {
        return protocol;
    }

    public long getMaxPlayers() {
        return maxPlayers;
    }

    public long getOnlinePlayerCount() {
        return onlinePlayerCount;
    }

    public String getSamplePlayerName() {
        return samplePlayerName;
    }

    public String getSamplePlayerUUID() {
        return samplePlayerUUID;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public String getFavIcon() {
        return favIcon;
    }
}
