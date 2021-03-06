package ninja.seibert.m3c.packets.v340.login.receiving;

import ninja.seibert.m3c.Client;
import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class Disconnect00 extends ReceivingPacket {

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        String reason = readString(buff);
        Client.getLogger().warning("Kicked for " + reason);
    }
}
