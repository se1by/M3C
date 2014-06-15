package com.bitbyterstudios.m3c;

public class ClientData {
    private String user;
    private String uuid;
    private String clientToken;
    private String accessToken;

    private boolean spawned;

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
