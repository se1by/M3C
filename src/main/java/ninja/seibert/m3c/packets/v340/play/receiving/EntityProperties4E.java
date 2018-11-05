package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.util.Utilities;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntityProperties4E extends ReceivingPacket {
    private int entityId;
    private EntityProperty[] properties;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.entityId = readVarInt(buff);
        this.properties = new EntityProperty[buff.getInt()];
        for (int i = 0; i < this.properties.length; i++) {
            this.properties[i] = EntityProperty.fromByteBuffer(buff);
        }
    }

    public int getEntityId() {
        return entityId;
    }

    public EntityProperty[] getProperties() {
        return properties;
    }

    }

class EntityProperty {
    private String key;
    private double value;

    private List<Modifier> modifiers;

    public EntityProperty(String key, double value, List<Modifier> modifiers) {
        this.key = key;
        this.value = value;
        this.modifiers = modifiers;
    }

    public String getKey() {
        return key;
    }

    public double getValue() {
        return value;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }

    public static EntityProperty fromByteBuffer(ByteBuffer buffer) {
        String key = Utilities.readVarIntPrefixedStringFromByteBuffer(buffer);
        double value = buffer.getDouble();
        List<Modifier> modifiers = new ArrayList<>();
        for (int i = Utilities.readVarInt(buffer); i > 0; i--) {
            modifiers.add(Modifier.fromByteBuffer(buffer));
        }
        return new EntityProperty(key, value, modifiers);
    }
}

class Modifier {
    private UUID uuid;
    private double amount;
    private byte operation;

    public Modifier(UUID uuid, double amount, byte operation) {
        this.uuid = uuid;
        this.amount = amount;
        this.operation = operation;
    }

    public UUID getUuid() {
        return uuid;
    }

    public double getAmount() {
        return amount;
    }

    public byte getOperation() {
        return operation;
    }

    public static Modifier fromByteBuffer(ByteBuffer buffer) {
        UUID uuid = new UUID(buffer.getLong(), buffer.getLong());
        double amount = buffer.getDouble();
        byte operation = buffer.get();
        return new Modifier(uuid, amount, operation);
    }
}