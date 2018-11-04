package ninja.seibert.m3c.util;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class EntityMetadata {

    private HashMap<Byte, Object> metadata;

    public EntityMetadata(HashMap<Byte, Object> metadata) {
        this.metadata = metadata;
    }

    public Object getData(byte key) {
        return getMetadata().get(key);
    }

    public HashMap<Byte, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<Byte, Object> metadata) {
        this.metadata = metadata;
    }

    public static EntityMetadata fromByteBuffer(ByteBuffer buffer) {
        HashMap<Byte, Object> data = new HashMap<>();
        while (buffer.hasRemaining()) {
            byte item = buffer.get();
            byte key = (byte) (item & 0x1F);
            byte type = (byte) (item & 0xE0);
            switch (type) {
                case 0:
                    data.put(key, buffer.get());
                    break;
                case 1:
                    data.put(key, buffer.getShort());
                    break;
                case 2:
                    data.put(key, buffer.getInt());
                    break;
                case 3:
                    data.put(key, buffer.getFloat());
                    break;
                case 4:
                    data.put(key, Utilities.readVarIntPrefixedStringFromByteBuffer(buffer));
                    break;
                case 5:
                    data.put(key, Slot.fromByteBuffer(buffer));
                    break;
                case 6:
                    int[] vector = new int[3];
                    vector[0] = buffer.getInt();
                    vector[1] = buffer.getInt();
                    vector[2] = buffer.getInt();
                    data.put(key, vector);
                    break;
                case 7:
                    float[] floatVector = new float[3];
                    floatVector[0] = buffer.getFloat();
                    floatVector[1] = buffer.getFloat();
                    floatVector[2] = buffer.getFloat();
                    data.put(key, floatVector);
                    break;
            }
        }
        return new EntityMetadata(data);
    }
}
