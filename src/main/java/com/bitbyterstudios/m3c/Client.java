package com.bitbyterstudios.m3c;

import com.bitbyterstudios.m3c.util.LogFormatter;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private static final Logger logger = Logger.getLogger("M3C");

    public void start() throws IOException {
        Console console = System.console();
        if (console == null) {
            logger.severe("Couldn't get Console instance");
            //System.exit(0);
        }
        send("Started MC-Console-Client v0.1 ...");
        String user = null;
        String pass = null;

        File config = new File("m3c.yml");
        if (config.exists()) {
            Yaml yaml = new Yaml();
            Map map = (Map) yaml.load(new FileInputStream(config));
            user = (String) map.get("user");
            pass = (String) map.get("pass");
        }

        if (user == null || user.isEmpty()) {
            send("username: ");
            user = console.readLine();
        }

        if (pass == null || pass.isEmpty()) {
            send("password: ");
            pass = String.valueOf(console.readPassword());

        }
        ClientData data = ApiAccess.authenticate(user, pass);

        ServerHandler serverHandler = new ServerHandler(this, data);
        serverHandler.init("localhost", 25565);
        serverHandler.listen();
        ApiAccess.invalidate(data);
    }

    public void send(String msg) {
        System.out.println(msg);
        getLogger().info(msg);
    }

    public static Logger getLogger() {
        return logger;
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
            consoleHandler.setLevel(Level.FINEST);
        } catch (IOException e) {
            System.out.println("Couldn't setup logger!");
            e.printStackTrace();
        }
        new Client().start();
    }
}
