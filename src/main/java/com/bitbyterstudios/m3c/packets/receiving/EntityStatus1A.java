package com.bitbyterstudios.m3c.packets.receiving;

import com.bitbyterstudios.m3c.ServerHandler;

import java.nio.ByteBuffer;

public class EntityStatus1A extends ReceivingPacket {
    private int entityId;
    private byte status;

//    1	Sent when resetting a mob spawn minecart's timer / Rabbit jump animation
//    2	Living Entity hurt
//    3	Living Entity dead
//    4	Iron Golem throwing up arms
//    6	Wolf/Ocelot/Horse taming — Spawn “heart” particles
//    7	Wolf/Ocelot/Horse tamed — Spawn “smoke” particles
//    8	Wolf shaking water — Trigger the shaking animation
//    9	(of self) Eating accepted by server
//    10	Sheep eating grass
//    10	Play TNT ignite sound
//    11	Iron Golem handing over a rose
//    12	Villager mating — Spawn “heart” particles
//    13	Spawn particles indicating that a villager is angry and seeking revenge
//    14	Spawn happy particles near a villager
//    15	Witch animation — Spawn “magic” particles
//    16	Play zombie converting into a villager sound
//    17	Firework exploding
//    18	Animal in love (ready to mate) — Spawn “heart” particles
//    19	Reset squid rotation
//    20	Spawn explosion particle — works for some living entities
//    21	Play guardian sound — works for every entity
//    22	Enables reduced debug for players
//    23	Disables reduced debug for players

    @Override
    public void handle(ByteBuffer buff, ServerHandler handler) {
        entityId = readVarInt(buff);
        status = buff.get();
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
