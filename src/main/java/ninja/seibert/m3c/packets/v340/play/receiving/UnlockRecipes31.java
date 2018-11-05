package ninja.seibert.m3c.packets.v340.play.receiving;

import ninja.seibert.m3c.ConnectionHandler;
import ninja.seibert.m3c.packets.ReceivingPacket;

import java.nio.ByteBuffer;

public class UnlockRecipes31 extends ReceivingPacket {
    private int action;
    private boolean craftingBookOpen;
    private boolean filteringCraftable;
    private int[] recipeIds;
    private int[] recipeIds2;


    @Override
    public void handle(ByteBuffer buff, ConnectionHandler handler) {
        this.action = readVarInt(buff);
        this.craftingBookOpen = buff.get() == 1;
        this.filteringCraftable = buff.get() == 1;

        recipeIds = new int[readVarInt(buff)];
        for (int i = 0; i < recipeIds.length; i++) {
            recipeIds[i] = readVarInt(buff);
        }

        if (action == 0) {
            recipeIds2 = new int[readVarInt(buff)];
            for (int i = 0; i < recipeIds2.length; i++) {
                recipeIds2[i] = readVarInt(buff);
            }
        }
    }

    public int getAction() {
        return action;
    }

    public boolean isCraftingBookOpen() {
        return craftingBookOpen;
    }

    public boolean isFilteringCraftable() {
        return filteringCraftable;
    }

    public int[] getRecipeIds() {
        return recipeIds;
    }

    public int[] getRecipeIds2() {
        return recipeIds2;
    }
}
