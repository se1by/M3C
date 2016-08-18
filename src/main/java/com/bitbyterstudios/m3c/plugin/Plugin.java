package com.bitbyterstudios.m3c.plugin;

import com.bitbyterstudios.m3c.Client;

public interface Plugin {

    void onEnable(Client client);

    String getName();

}
