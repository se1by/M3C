package ninja.seibert.m3c.packets.definitions.login.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.io.IOException;

public abstract class EncryptionRequest extends ReceivingPacket {
    public abstract void encrypt(ConnectionHandler handler) throws IOException;
}
