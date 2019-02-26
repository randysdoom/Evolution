package com.randysdoom.evolution.api.block.machine;

import net.minecraft.util.IStringSerializable;

public enum MachineTier implements IStringSerializable
{
    BASIC(0, "basic"),
    INTERMEDIATE(1, "intermediate"),
    ADVANCED(2, "advanced"),
    COMPLEX(3, "complex");

    private int tierID;
    private String tierName;

    MachineTier(int tierID, String tierName)
    {
        this.tierID = tierID;
        this.tierName = tierName;
    }

    public int getTierID()
    {
        return tierID;
    }

    public String getName()
    {
        return tierName;
    }
}
