package com.bitbyterstudios.m3c.plugin;

import com.bitbyterstudios.m3c.Client;
import com.bitbyterstudios.m3c.ServerHandler;
import com.bitbyterstudios.m3c.packets.receiving.ReceivingPacket;

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

    public void addListener(Class<? extends ReceivingPacket> clazz, Listener listener) {
        List<Listener> list = listeners.get(clazz);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(listener);
        listeners.put(clazz, list);
    }

    @SuppressWarnings("unchecked")
    public void callListeners(ReceivingPacket packet, ServerHandler handler) {
        //for (Listener<? extends ReceivingPacket> listener : listeners.get(packet.getClass())) listener.handle(packet);
        if (listeners.get(packet.getClass()) == null) return;
        listeners.get(packet.getClass()).forEach((Listener listener1) ->
                listener1.handle(packet, handler));
    }
}
