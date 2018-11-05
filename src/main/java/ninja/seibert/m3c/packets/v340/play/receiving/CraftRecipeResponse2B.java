package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class CraftRecipeResponse2B extends ReceivingPacket {
    private byte windowId;
    private int recipeId;

    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.windowId = buff.get();
        this.recipeId = readVarInt(buff);
    }

    public byte getWindowId() {
        return windowId;
    }

    public int getRecipeId() {
        return recipeId;
    }
}
