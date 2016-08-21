package com.bitbyterstudios.m3c;

import com.bitbyterstudios.m3c.listener.KeepAliveListener;
import com.bitbyterstudios.m3c.listener.SetCompressionListener;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.KeepAlive00;
import com.bitbyterstudios.m3c.packets.v47.login.receiving.SetCompression03;
import com.bitbyterstudios.m3c.plugin.PluginManager;
import com.bitbyterstudios.m3c.util.LogFormatter;
import com.bitbyterstudios.m3c.util.Utilities;
import org.yaml.snakeyaml.Yaml;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private static final Logger logger = Logger.getLogger("M3C");

    private PluginManager pluginManager;

    private String server;
    private int port;

    private String user;

    public Client() {
        pluginManager = new PluginManager(this);
    }

    public void start(String[] args) throws IOException {
        Console console = System.console();
        if (console == null) {
            logger.severe("Couldn't get Console instance");
            //System.exit(0);
        }
        send("Started MC-Console-Client v0.2 ...");
        String pass = null;

        File config = new File("m3c.yml");
        if (config.exists()) {
            Yaml yaml = new Yaml();
            Map map = (Map) yaml.load(new FileInputStream(config));
            user = (String) map.get("user");
            pass = (String) map.get("pass");
            server = (String) map.get("host");
            port = map.containsKey("port") ? (Integer) map.get("port") : 25565;
        }

        if (user == null || user.isEmpty()) {
            send("username: ");
            user = console.readLine();
        }

        if (pass == null || pass.isEmpty()) {
            send("password: ");
            pass = String.valueOf(console.readPassword());
        }

        if (server == null || server.isEmpty()) {
            server = "mc.hypixel.net";
        }
        if (args.length > 0) {
            server = args[0];
        }

        if (port == 0) {
            port = 25565;
        }
        if (args.length > 1 && Utilities.isInt(args[1])) {
            port = Integer.parseInt(args[1]);
        }

        pluginManager.load();
        pluginManager.addListener(KeepAlive00.class, new KeepAliveListener());
        pluginManager.addListener(SetCompression03.class, new SetCompressionListener());

        getLogger().info("Authenticating...");
        ClientData data = ApiAccess.authenticate(user, pass);

        getLogger().info("Connecting to " + server + ":" + port);
        ConnectionHandler connectionHandler = new ConnectionHandler(this, data);
        connectionHandler.init(server, port);
        connectionHandler.listen();
        ApiAccess.invalidate(data);
    }

    public void send(String msg) {
        //System.out.println(msg);
        getLogger().info(msg);
    }

    public static Logger getLogger() {
        return logger;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return "lolnope";
    }

    public static void main(String[] args) throws IOException {
        logger.setUseParentHandlers(false);
        try {
            FileHandler fileHandler = new FileHandler("latest.log");
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new LogFormatter());
            fileHandler.setLevel(Level.FINEST);
            logger.setLevel(Level.FINEST);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            logger.addHandler(consoleHandler);
            consoleHandler.setFormatter(new LogFormatter());
            consoleHandler.setLevel(Level.INFO);
        } catch (IOException ioe) {
            System.err.println("Couldn't setup logger!");
            ioe.printStackTrace();
        }
        new Client().start(args);
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }
}
