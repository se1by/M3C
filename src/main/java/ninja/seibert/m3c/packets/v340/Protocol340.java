package ninja.seibert.m3c.packets.v340;

import ninja.seibert.m3c.packets.Protocol;
import ninja.seibert.m3c.packets.ReceivingPacket;
import ninja.seibert.m3c.packets.v340.login.receiving.Disconnect00;
import ninja.seibert.m3c.packets.v340.login.receiving.EncryptionRequest01;
import ninja.seibert.m3c.packets.v340.login.receiving.LoginSuccess02;
import ninja.seibert.m3c.packets.v340.login.receiving.SetCompression03;
import ninja.seibert.m3c.packets.v340.play.receiving.*;

import java.util.HashMap;

public class Protocol340 implements Protocol {

    private HashMap<Integer, Class<? extends ReceivingPacket>> loginPackets = new HashMap<>();
    private HashMap<Integer, Class<? extends ReceivingPacket>> playPackets = new HashMap<>();

    public Protocol340() {
        loadPackets();
    }

    @Override
    public ReceivingPacket getLoginPacket(int id) {
        Class<? extends ReceivingPacket> packetClass = loginPackets.get(id);
        if (packetClass == null) {
            return null;
        }

        try {
            return packetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ReceivingPacket getPlayPacket(int id) {
        Class<? extends ReceivingPacket> packetClass = playPackets.get(id);
        if (packetClass == null) {
            return null;
        }

        try {
            return packetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getVersion() {
        return 340;
    }

    @Override
    public String getMinecraftVersion() {
        return "1.12.2";
    }

    private void loadPackets() {
        loginPackets.put(0, Disconnect00.class);
        loginPackets.put(1, EncryptionRequest01.class);
        loginPackets.put(2, LoginSuccess02.class);
        loginPackets.put(0x03, SetCompression03.class);

        playPackets.put(0x00, SpawnObject00.class);
        playPackets.put(0x01, SpawnExperienceOrbs01.class);
        playPackets.put(0x02, SpawnGlobalEntity02.class);
        playPackets.put(0x03, SpawnMob03.class);
        playPackets.put(0x04, SpawnPainting04.class);
        playPackets.put(0x05, SpawnPlayer05.class);
        playPackets.put(0x06, Animation06.class);
        playPackets.put(0x07, Statistics07.class);
        playPackets.put(0x08, BlockBreak08.class);
        playPackets.put(0x09, UpdateBlockEntity09.class);
        playPackets.put(0x0A, BlockAction0A.class);
        playPackets.put(0x0B, BlockChange0B.class);
        playPackets.put(0x0C, BossBar0C.class);
        playPackets.put(0x0D, ServerDifficulty0D.class);
        playPackets.put(0x0E, TabComplete0E.class);
        playPackets.put(0x0F, Chat0F.class);
        playPackets.put(0x10, MultiBlockChange10.class);
        playPackets.put(0x11, ConfirmTransaction11.class);
        playPackets.put(0x12, CloseWindow12.class);
        playPackets.put(0x13, OpenWindow13.class);
        playPackets.put(0x14, WindowItems14.class);
        playPackets.put(0x15, WindowProperty15.class);
        playPackets.put(0x16, SetSlot16.class);
        playPackets.put(0x17, SetCooldown17.class);
        playPackets.put(0x18, PluginMessage18.class);
        playPackets.put(0x19, NamedSoundEffect19.class);
        playPackets.put(0x1A, Disconnect1A.class);
        playPackets.put(0x1B, EntityStatus1A.class);
        playPackets.put(0x1C, Explosion1C.class);
        playPackets.put(0x1D, UnloadChunk1D.class);
        playPackets.put(0x1E, ChangeGamestate1E.class);
        playPackets.put(0x1F, KeepAlive1F.class);
        playPackets.put(0x20, ChunkData20.class);
        playPackets.put(0x21, Effect21.class);
        playPackets.put(0x22, Particle22.class);
        playPackets.put(0x23, JoinGame23.class);
        playPackets.put(0x24, Map24.class);
        playPackets.put(0x25, Entity25.class);
        playPackets.put(0x26, EntityRelativeMove26.class);
        playPackets.put(0x27, EntityLookAndRelativeMove27.class);
        playPackets.put(0x28, EntityLook28.class);
        playPackets.put(0x29, VehicleMove29.class);
        playPackets.put(0x2A, OpenSignEditor2A.class);
        playPackets.put(0x2B, CraftRecipeResponse2B.class);
        playPackets.put(0x2C, PlayerAbilities2C.class);
        playPackets.put(0x2D, CombatEvent2D.class);
        playPackets.put(0x2E, PlayerListItem2E.class);
        playPackets.put(0x2F, PlayerPositionLook2F.class);
        playPackets.put(0x30, UseBed30.class);
        playPackets.put(0x31, UnlockRecipes31.class);
        playPackets.put(0x32, DestroyEntities32.class);
        playPackets.put(0x33, RemoveEntityEffect33.class);
        playPackets.put(0x34, ResourcepackSend34.class);
        playPackets.put(0x35, Respawn35.class);
        playPackets.put(0x36, EntityHeadLook36.class);
        playPackets.put(0x37, SelectAdvancementTab37.class);
        playPackets.put(0x38, WorldBorder38.class);
        playPackets.put(0x39, Camera39.class);
        playPackets.put(0x3A, HeldItemChange3A.class);
        playPackets.put(0x3B, DisplayScoreboard3B.class);
        playPackets.put(0x3C, EntityMetadata3C.class);
        playPackets.put(0x3D, AttachEntity3D.class);
        playPackets.put(0x3E, EntityVelocity3E.class);
        playPackets.put(0x3F, EntityEquipment3F.class);
        playPackets.put(0x40, SetExperience40.class);
        playPackets.put(0x41, SetHealth41.class);
        playPackets.put(0x42, ScoreboardObjective42.class);
        playPackets.put(0x43, SetPassengers43.class);
        playPackets.put(0x44, Teams44.class);
        playPackets.put(0x45, UpdateScore45.class);
        playPackets.put(0x46, SpawnPosition46.class);
        playPackets.put(0x47, Time47.class);
        playPackets.put(0x48, Title48.class);
        playPackets.put(0x49, SoundEffect49.class);
        playPackets.put(0x4A, PlayerListHeaderAndFooter4A.class);
        playPackets.put(0x4B, CollectItem4B.class);
        playPackets.put(0x4C, EntityTeleport4C.class);
        playPackets.put(0x4D, Advancements4D.class);
        playPackets.put(0x4E, EntityProperties4E.class);
        playPackets.put(0x4F, EntityEffect4F.class);

//        playPackets.put(0x26, MapChunkBulk26.class);
//        playPackets.put(0x46, SetCompression46.class);
//        playPackets.put(0x49, UpdateEntityNbt49.class);
    }
}
