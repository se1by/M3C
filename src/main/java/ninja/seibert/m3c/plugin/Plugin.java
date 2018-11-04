package ninja.seibert.m3c.plugin;

import ninja.seibert.m3c.Client;

public interface Plugin {

    void onEnable(Client client);

    String getName();

}
