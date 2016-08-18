package com.bitbyterstudios.m3c.plugin;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginManager {
    private Client client;
    private Map<String, Plugin> plugins;
    private Map<Class<? extends ReceivingPacket>, List<Listener>> listeners;
    private PluginLoader loader;

    public PluginManager(Client client) {
        this.client = client;

        plugins = new HashMap<>();
        listeners = new HashMap<>();
        loader = new PluginLoader();
    }

    public void load() {
        Client.getLogger().info("Loading plugins...");
        File pluginDir = new File("plugins");
        if (!pluginDir.exists()) {
            Client.getLogger().info("Plugin directory does not exists, creating...");
            if (!pluginDir.mkdirs()) {
                Client.getLogger().severe("Could not create plugin directory!");
            }
            return;
        }
        if (!pluginDir.isDirectory()) {
            Client.getLogger().warning(pluginDir.getAbsolutePath() + " is not a directory!");
            return;
        }

        for (File f : pluginDir.listFiles()) {
            Plugin plugin = loader.loadPlugin(f);
            if (plugins.containsKey(plugin.getName())) {
                Client.getLogger().warning("Already loaded a plugin called " + plugin.getName());
                continue;
            }
            plugin.onEnable(client);
            Client.getLogger().info("Enabled " + plugin.getName());
            plugins.put(plugin.getName(), plugin);
        }
        Client.getLogger().info("Plugins loaded!");
    }

    public void addListener(Class<? extends ReceivingPacket> clazz, Listener<? extends ReceivingPacket> listener) {
        List<Listener> list = listeners.get(clazz);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(listener);
        listeners.put(clazz, list);
    }

    @SuppressWarnings("unchecked")
    public void callListeners(ReceivingPacket packet, ServerHandler handler) {
        if (listeners.get(packet.getClass()) == null) {
            return;
        }
        listeners.get(packet.getClass()).forEach(listener1 ->
                listener1.handle(packet, handler));
    }
}
