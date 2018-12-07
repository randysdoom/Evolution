package com.evolution.api.block.machine;

import net.minecraft.util.IStringSerializable;

public enum MachineTier implements IStringSerializable
{
    BASIC(0, "basic"),
    INTERMEDIATE(1, "intermediate"),
    ADVANCED(2, "advanced"),
    COMPLEX(3, "complex");

    private int TIER_ID;
    private String TIER_NAME;

    MachineTier(int TIER_ID, String TIER_NAME)
    {
        this.TIER_ID = TIER_ID;
        this.TIER_NAME = TIER_NAME;
    }

    public int getTierID() { return TIER_ID; }

    public String getName() { return TIER_NAME; }
}
