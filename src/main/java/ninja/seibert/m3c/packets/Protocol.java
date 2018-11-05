package ninja.seibert.m3c.packets;

import ninja.seibert.m3c.packets.v340.Protocol340;
import ninja.seibert.m3c.packets.v47.Protocol47;

public interface Protocol {

    ReceivingPacket getLoginPacket(int id);

    ReceivingPacket getPlayPacket(int id);

    int getVersion();

    String getMinecraftVersion();

    static Protocol getProtocol(int version) {
        switch (version) {
            case 47:
                return new Protocol47();
            case 340:
                return new Protocol340();
            default:
                throw new UnsupportedOperationException("Protocol " + version + " not yet implemented!");
        }
    }

    static Protocol getLatestProtocol() {
        return new Protocol340();
    }
}
