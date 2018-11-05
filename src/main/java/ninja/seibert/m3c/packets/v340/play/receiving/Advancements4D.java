package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.Slot;
import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class Advancements4D extends ReceivingPacket {
    private boolean shouldReset;
    private Map<String, Advancement> advancements;
    private String[] advancementIdentifiersToRemove;
    private Map<String,Criteria[]> advancementProgress;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.shouldReset = buff.get() == 1;

        this.advancements = new HashMap<>();
        int size = readVarInt(buff);
        for (int i = 0; i < size; i++) {
            readVarInt(buff); // ignore the "array" length
            advancements.put(readString(buff), Advancement.fromByteBuffer(buff));
        }

        this.advancementIdentifiersToRemove = new String[readVarInt(buff)];
        for (int i = 0; i < this.advancementIdentifiersToRemove.length; i++) {
            this.advancementIdentifiersToRemove[i] = readString(buff);
        }

        this.advancementProgress = new HashMap<>();
        size = readVarInt(buff);
        for (int i = 0; i < size; i++) {
            readVarInt(buff); // ignore the "array" length
            String identifier = readString(buff);
            Criteria[] criteria = new Criteria[readVarInt(buff)];
            for (int j = 0; j < criteria.length; j++) {
                criteria[j] = Criteria.fromByteBuffer(buff);
            }
            this.advancementProgress.put(identifier, criteria);
        }
    }

    public boolean isShouldReset() {
        return shouldReset;
    }

    public Map<String, Advancement> getAdvancements() {
        return advancements;
    }

    public String[] getAdvancementIdentifiersToRemove() {
        return advancementIdentifiersToRemove;
    }

    public Map<String, Criteria[]> getAdvancementProgress() {
        return advancementProgress;
    }
}

class Advancement {
    private String parentIdentifier;
    private AdvancementDisplay display;
    // Using void here for now as it will get expanded later
    private Map<String, Void> criteria;
    private String[][] requirements;


    public String getParentIdentifier() {
        return parentIdentifier;
    }

    public AdvancementDisplay getDisplay() {
        return display;
    }

    public Map<String, Void> getCriteria() {
        return criteria;
    }

    public String[][] getRequirements() {
        return requirements;
    }

    public static Advancement fromByteBuffer(ByteBuffer buffer) {
        Advancement advancement = new Advancement();
        if (buffer.get() == 1) {
            advancement.parentIdentifier = Utilities.readVarIntPrefixedStringFromByteBuffer(buffer);
        }
        if (buffer.get() == 1) {
            advancement.display = AdvancementDisplay.fromByteBuffer(buffer);
        }

        advancement.criteria = new HashMap<>();
        int size = Utilities.readVarInt(buffer);
        for (int i = 0; i < size; i++) {
            advancement.criteria.put(Utilities.readVarIntPrefixedStringFromByteBuffer(buffer), null);
        }

        advancement.requirements = new String[Utilities.readVarInt(buffer)][];
        for (int i = 0; i < advancement.requirements.length; i++) {
            advancement.requirements[i] = new String[Utilities.readVarInt(buffer)];
            for (int j = 0; j < advancement.requirements[i].length; j++) {
                advancement.requirements[i][j] = Utilities.readVarIntPrefixedStringFromByteBuffer(buffer);
            }
        }

        return advancement;
    }
}

class AdvancementDisplay {
    private String titleJson;
    private String descriptionJson;
    private Slot icon;
    private int frameType;
    private int flags;
    private String backgroundTexture;
    private float x;
    private float y;

    public String getTitleJson() {
        return titleJson;
    }

    public String getDescriptionJson() {
        return descriptionJson;
    }

    public Slot getIcon() {
        return icon;
    }

    public int getFrameType() {
        return frameType;
    }

    public int getFlags() {
        return flags;
    }

    public String getBackgroundTexture() {
        return backgroundTexture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public static AdvancementDisplay fromByteBuffer(ByteBuffer buffer) {
        AdvancementDisplay display = new AdvancementDisplay();
        display.titleJson = Utilities.readVarIntPrefixedStringFromByteBuffer(buffer);
        display.descriptionJson = Utilities.readVarIntPrefixedStringFromByteBuffer(buffer);
        display.icon = Slot.fromByteBuffer(buffer);
        display.frameType = Utilities.readVarInt(buffer);
        display.flags = buffer.getInt();
        if (display.flags == 0x01) {
            display.backgroundTexture = Utilities.readVarIntPrefixedStringFromByteBuffer(buffer);
        }
        display.x = buffer.getFloat();
        display.y = buffer.getFloat();

        return display;
    }
}

class Criteria {
    private String identifier;
    private long achievementDate;

    public String getIdentifier() {
        return identifier;
    }

    public long getAchievementDate() {
        return achievementDate;
    }

    public static Criteria fromByteBuffer(ByteBuffer buffer) {
        Utilities.readVarInt(buffer); // ignore "array" length

        Criteria criteria = new Criteria();
        criteria.identifier = Utilities.readVarIntPrefixedStringFromByteBuffer(buffer);
        if (buffer.get() == 1) {
            criteria.achievementDate = buffer.getLong();
        }
        return criteria;
    }
}
