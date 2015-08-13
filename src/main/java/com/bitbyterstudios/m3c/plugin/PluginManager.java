package com.bitbyterstudios.m3c.plugin;

import com.bitbyterstudios.m3c.Client;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PluginManager {
    private Client client;
    private Map<String, Plugin> plugins;
    private PluginLoader loader;

    public PluginManager(Client client) {
        this.client = client;

        plugins = new HashMap<String, Plugin>();
        loader = new PluginLoader();
    }

    public void load() {
        Client.getLogger().info("Loading plugins...");
        File pluginDir = new File("plugins");
        if (!pluginDir.exists()) {
            Client.getLogger().info("Plugin directory does not exists, creating...");
            pluginDir.mkdirs();
            return;
        }
        if (!pluginDir.isDirectory()) {
            Client.getLogger().warning(pluginDir.getAbsolutePath() + " is not a directory!");
            return;
        }

        for (File f : pluginDir.listFiles()) {
            Plugin p = loader.loadPlugin(f);
            if (plugins.containsKey(p.getName())) {
                Client.getLogger().warning("Already loaded a plugin called " + p.getName());
                continue;
            }
            p.onEnable(client);
            Client.getLogger().info("Enabled " + p.getName());
            plugins.put(p.getName(), p);
        }
        Client.getLogger().info("Plugins loaded!");
    }
}
