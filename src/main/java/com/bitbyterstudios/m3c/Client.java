package com.bitbyterstudios.m3c;

import com.bitbyterstudios.m3c.util.LogFormatter;

import java.io.Console;
import java.io.IOException;
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
        send("username: ");
        //String user = console.readLine();
        send("password: ");
        //String pass = String.valueOf(console.readPassword());
        String user = "<mail>"; //Used in development as IDE doesn't provide System.console()
        String pass = "<pass>";
        ClientData data = ApiAccess.authenticate(user, pass);

        ServerHandler serverHandler = new ServerHandler(this, data);
        serverHandler.init("<ip>", 25565);
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
            logger.setLevel(Level.FINER);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            logger.addHandler(consoleHandler);
            consoleHandler.setFormatter(new LogFormatter());
            consoleHandler.setLevel(Level.FINER);
        } catch (IOException e) {
            System.out.println("Couldn't setup logger!");
            e.printStackTrace();
        }
        new Client().start();
    }
}
