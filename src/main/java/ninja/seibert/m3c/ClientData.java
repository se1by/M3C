package ninja.seibert.m3c;

import ninja.seibert.m3c.util.Location;

public class ClientData {
    private String user;
    private String uuid;
    private int entityId;
    private Location location;
    private String clientToken;
    private String accessToken;
    private boolean spawned;

    public ClientData() {
        this.location = new Location();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getEntityId() {
        return entityId;
    }

    public Location getLocation() {
        return location;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isSpawned() {
        return spawned;
    }

    public void setSpawned(boolean isSpawned) {
        this.spawned = isSpawned;
    }
}
