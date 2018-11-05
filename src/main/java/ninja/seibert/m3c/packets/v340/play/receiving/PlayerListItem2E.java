package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;
import java.util.UUID;

public class PlayerListItem2E extends ReceivingPacket {
    private int action;
    private PlayerListItemPlayer[] players;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.action = readVarInt(buff);
        this.players = new PlayerListItemPlayer[readVarInt(buff)];


        for (int i = 0; i < this.players.length; i++) {
            UUID uuid = readUUID(buff);
            switch (this.action) {
                case 0:
                    String name = readString(buff);

                    Property[] properties = new Property[readVarInt(buff)];
                    for (int j = 0; j < properties.length; j++) {
                        properties[j] = Property.fromByteBuffer(buff);
                    }

                    int gamemode = readVarInt(buff);
                    int ping = readVarInt(buff);
                    boolean hasDisplayName = buff.get() == 1;
                    String displayName = null;
                    if (hasDisplayName) {
                        displayName = readString(buff);
                    }
                    this.players[i] = new PlayerListItemPlayer(uuid, name, properties, gamemode, ping, hasDisplayName, displayName);
                    break;
                case 1:
                    gamemode = Utilities.readVarInt(buff);
                    this.players[i] = new PlayerListItemPlayer(uuid, null, null, gamemode, -1, false, null);
                    break;
                case 2:
                    ping = Utilities.readVarInt(buff);
                    this.players[i] = new PlayerListItemPlayer(uuid, null, null, -1, ping, false, null);
                    break;
                case 3:
                    hasDisplayName = buff.get() == 1;
                    if (hasDisplayName) {
                        displayName = Utilities.readVarIntPrefixedStringFromByteBuffer(buff);
                    } else {
                        displayName = null;
                    }
                    this.players[i] = new PlayerListItemPlayer(uuid, null, null, -1, -1, hasDisplayName, displayName);
                    break;
                case 4:
                    break;
                    default:
                        throw new IllegalStateException("Unknown action " + this.action + "!");
            }
        }
    }

    public int getAction() {
        return action;
    }

    public PlayerListItemPlayer[] getPlayers() {
        return players;
    }
}

class PlayerListItemPlayer {
    private UUID uuid;
    private String name;
    private Property[] properties;
    private int gamemode;
    private int ping;
    private boolean hasDisplayName;
    private String displayName;

    public PlayerListItemPlayer(UUID uuid, String name, Property[] properties, int gamemode, int ping,
                                boolean hasDisplayName, String displayName) {
        this.uuid = uuid;
        this.name = name;
        this.properties = properties;
        this.gamemode = gamemode;
        this.ping = ping;
        this.hasDisplayName = hasDisplayName;
        this.displayName = displayName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Property[] getProperties() {
        return properties;
    }

    public void setProperties(Property[] properties) {
        this.properties = properties;
    }

    public int getGamemode() {
        return gamemode;
    }

    public void setGamemode(int gamemode) {
        this.gamemode = gamemode;
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    public boolean hasDisplayName() {
        return hasDisplayName;
    }

    public void setHasDisplayName(boolean hasDisplayName) {
        this.hasDisplayName = hasDisplayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}

class Property {
    private String name;
    private String value;
    private boolean isSigned;
    private String signature;

    public Property(String name, String value, boolean isSigned, String signature) {
        this.name = name;
        this.value = value;
        this.isSigned = isSigned;
        this.signature = signature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public static Property fromByteBuffer(ByteBuffer buffer) {
        String name = Utilities.readVarIntPrefixedStringFromByteBuffer(buffer);
        String value = Utilities.readVarIntPrefixedStringFromByteBuffer(buffer);
        boolean isSigned = buffer.get() == 1;
        String signature = null;
        if (isSigned) {
            signature = Utilities.readVarIntPrefixedStringFromByteBuffer(buffer);
        }
        return new Property(name, value, isSigned, signature);
    }
}
