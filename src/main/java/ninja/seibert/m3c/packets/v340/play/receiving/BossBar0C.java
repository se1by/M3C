package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;
import java.util.UUID;

// see https://wiki.vg/index.php?title=Protocol#Boss_Bar for mappings
public class BossBar0C extends ReceivingPacket {
    private UUID barUuid;
    private int actionId;
    private String titleJson; // type chat
    private float health;
    private int color;
    private int division;
    private byte flags;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.barUuid = readUUID(buff);
        this.actionId = readVarInt(buff);
        switch (actionId) {
            case 0: // Add
                this.titleJson = readString(buff);
                this.health = buff.getFloat();
                this.color = readVarInt(buff);
                this.division = readVarInt(buff);
                this.flags = buff.get();
                break;
            case 1: // Remove
                break;
            case 2: // Update health
                this.health = buff.getFloat();
                break;
            case 3: // Update title
                this.titleJson = readString(buff);
                break;
            case 4: // Update style
                this.color = readVarInt(buff);
                this.division = readVarInt(buff);
                break;
            case 5: // Update flags
                this.flags = buff.get();
                break;
            default:
                throw new IllegalStateException("Unknown actionId " + actionId + "!");
        }
    }

    public UUID getBarUuid() {
        return barUuid;
    }

    public int getActionId() {
        return actionId;
    }

    public String getTitleJson() {
        return titleJson;
    }

    public float getHealth() {
        return health;
    }

    public int getColor() {
        return color;
    }

    public int getDivision() {
        return division;
    }

    public byte getFlags() {
        return flags;
    }
}
