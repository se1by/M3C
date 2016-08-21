package com.bitbyterstudios.m3c.packets.v47;

import com.bitbyterstudios.m3c.packets.Protocol;
import com.bitbyterstudios.m3c.packets.ReceivingPacket;
import com.bitbyterstudios.m3c.packets.v47.login.receiving.Disconnect00;
import com.bitbyterstudios.m3c.packets.v47.login.receiving.EncryptionRequest01;
import com.bitbyterstudios.m3c.packets.v47.login.receiving.LoginSuccess02;
import com.bitbyterstudios.m3c.packets.v47.login.receiving.SetCompression03;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Animation0B;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.AttachEntity1B;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.BlockAction24;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.BlockBreak25;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.BlockChange23;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Camera43;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.ChangeGamestate2B;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Chat02;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.ChunkData21;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.CloseWindow2E;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.CollectItem0D;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.CombatEvent42;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.ConfirmTransaction32;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.DestroyEntity13;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Disconnect40;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.DisplayScoreboard3D;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Effect28;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Entity14;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityEffect1D;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityEquipment04;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityHeadLook19;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityLook16;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityLookAndRelativeMove17;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityMetadata1C;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityProperties20;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityRelativeMove15;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityStatus1A;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityTeleport18;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.EntityVelocity12;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Explosion27;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Health06;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.HeldItemChange09;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.JoinGame01;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.KeepAlive00;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Map34;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.MapChunkBulk26;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.MultiBlockChange22;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.OpenSignEditor36;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.OpenWindow2D;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Particle2A;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.PlayerAbilities39;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.PlayerListHeaderAndFooter47;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.PlayerListItem38;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.PlayerPositionLook08;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.PluginMessage3F;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.RemoveEntityEffect1E;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.ResourcepackSend48;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Respawn07;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.ScoreboardObjective3B;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.ServerDifficulty41;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SetCompression46;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SetExperience1F;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SetSlot2F;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SoundEffect29;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnExperienceOrbs11;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnGlobalEntity2C;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnMob0F;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnObject0E;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnPainting10;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnPlayer0C;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.SpawnPosition05;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Statistics37;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.TabComplete3A;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Teams3E;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Time03;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.Title45;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.UpdateBlockEntity35;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.UpdateEntityNbt49;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.UpdateScore3C;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.UpdateSign33;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.UseBed0A;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.WindowItems30;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.WindowProperty31;
import com.bitbyterstudios.m3c.packets.v47.play.receiving.WorldBorder44;

import java.util.HashMap;

public class Protocol47 implements Protocol {

    private HashMap<Integer, Class<? extends ReceivingPacket>> loginPackets = new HashMap<>();
    private HashMap<Integer, Class<? extends ReceivingPacket>> playPackets = new HashMap<>();

    public Protocol47() {
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
        return 47;
    }

    @Override
    public String getMinecraftVersion() {
        return "1.8.x";
    }

    private void loadPackets() {
        loginPackets.put(0, Disconnect00.class);
        loginPackets.put(1, EncryptionRequest01.class);
        loginPackets.put(2, LoginSuccess02.class);
        loginPackets.put(0x03, SetCompression03.class);

        playPackets.put(0x00, KeepAlive00.class);
        playPackets.put(0x01, JoinGame01.class);
        playPackets.put(0x02, Chat02.class);
        playPackets.put(0x03, Time03.class);
        playPackets.put(0x04, EntityEquipment04.class);
        playPackets.put(0x05, SpawnPosition05.class);
        playPackets.put(0x06, Health06.class);
        playPackets.put(0x07, Respawn07.class);
        playPackets.put(0x08, PlayerPositionLook08.class);
        playPackets.put(0x09, HeldItemChange09.class);
        playPackets.put(0x0A, UseBed0A.class);
        playPackets.put(0x0B, Animation0B.class);
        playPackets.put(0x0C, SpawnPlayer0C.class);
        playPackets.put(0x0D, CollectItem0D.class);
        playPackets.put(0x0E, SpawnObject0E.class);
        playPackets.put(0x0F, SpawnMob0F.class);
        playPackets.put(0x10, SpawnPainting10.class);
        playPackets.put(0x11, SpawnExperienceOrbs11.class);
        playPackets.put(0x12, EntityVelocity12.class);
        playPackets.put(0x13, DestroyEntity13.class);
        playPackets.put(0x14, Entity14.class);
        playPackets.put(0x15, EntityRelativeMove15.class);
        playPackets.put(0x16, EntityLook16.class);
        playPackets.put(0x17, EntityLookAndRelativeMove17.class);
        playPackets.put(0x18, EntityTeleport18.class);
        playPackets.put(0x19, EntityHeadLook19.class);
        playPackets.put(0x1A, EntityStatus1A.class);
        playPackets.put(0x1B, AttachEntity1B.class);
        playPackets.put(0x1C, EntityMetadata1C.class);
        playPackets.put(0x1D, EntityEffect1D.class);
        playPackets.put(0x1E, RemoveEntityEffect1E.class);
        playPackets.put(0x1F, SetExperience1F.class);
        playPackets.put(0x20, EntityProperties20.class);
        playPackets.put(0x21, ChunkData21.class);
        playPackets.put(0x22, MultiBlockChange22.class);
        playPackets.put(0x23, BlockChange23.class);
        playPackets.put(0x24, BlockAction24.class);
        playPackets.put(0x25, BlockBreak25.class);
        playPackets.put(0x26, MapChunkBulk26.class);
        playPackets.put(0x27, Explosion27.class);
        playPackets.put(0x28, Effect28.class);
        playPackets.put(0x29, SoundEffect29.class);
        playPackets.put(0x2A, Particle2A.class);
        playPackets.put(0x2B, ChangeGamestate2B.class);
        playPackets.put(0x2C, SpawnGlobalEntity2C.class);
        playPackets.put(0x2D, OpenWindow2D.class);
        playPackets.put(0x2E, CloseWindow2E.class);
        playPackets.put(0x2F, SetSlot2F.class);
        playPackets.put(0x30, WindowItems30.class);
        playPackets.put(0x31, WindowProperty31.class);
        playPackets.put(0x32, ConfirmTransaction32.class);
        playPackets.put(0x33, UpdateSign33.class);
        playPackets.put(0x34, Map34.class);
        playPackets.put(0x35, UpdateBlockEntity35.class);
        playPackets.put(0x36, OpenSignEditor36.class);
        playPackets.put(0x37, Statistics37.class);
        playPackets.put(0x38, PlayerListItem38.class);
        playPackets.put(0x39, PlayerAbilities39.class);
        playPackets.put(0x3A, TabComplete3A.class);
        playPackets.put(0x3B, ScoreboardObjective3B.class);
        playPackets.put(0x3C, UpdateScore3C.class);
        playPackets.put(0x3D, DisplayScoreboard3D.class);
        playPackets.put(0x3E, Teams3E.class);
        playPackets.put(0x3F, PluginMessage3F.class);
        playPackets.put(0x40, Disconnect40.class);
        playPackets.put(0x41, ServerDifficulty41.class);
        playPackets.put(0x42, CombatEvent42.class);
        playPackets.put(0x43, Camera43.class);
        playPackets.put(0x44, WorldBorder44.class);
        playPackets.put(0x45, Title45.class);
        playPackets.put(0x46, SetCompression46.class);
        playPackets.put(0x47, PlayerListHeaderAndFooter47.class);
        playPackets.put(0x48, ResourcepackSend48.class);
        playPackets.put(0x49, UpdateEntityNbt49.class);
    }
}
