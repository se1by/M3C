package com.bitbyterstudios.m3c.plugin;

import com.bitbyterstudios.m3c.Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class PluginLoader {

    public Plugin loadPlugin(File pluginFile) {
        if (!pluginFile.isFile()) {
            return null;
        }
        if (!pluginFile.getName().endsWith(".jar")) {
            Client.getLogger().warning("Unknown file " + pluginFile.getName() + " in plugin directory!");
            return null;
        }

        Plugin plugin = null;

        try {
            JarInputStream jis = new JarInputStream(new FileInputStream(pluginFile));
            JarEntry entry;
            while ((entry = jis.getNextJarEntry()) != null) {
                if (!entry.getName().toLowerCase().endsWith(".class")) {
                    continue;
                }

                ClassLoader cl = new URLClassLoader(new URL[]{pluginFile.toURI().toURL()});
                Class clazz = cl.loadClass(entry.getName().replace(".class", "").replaceAll("/", "."));
                if (isPlugin(clazz)) {
                    plugin = (Plugin) clazz.newInstance();
                }
            }
            return plugin;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isPlugin(Class clazz) {
        for (Class clazz1 : clazz.getInterfaces()) {
            if (clazz1.equals(Plugin.class)) {
                return true;
            }
        }
        return false;
    }
}
