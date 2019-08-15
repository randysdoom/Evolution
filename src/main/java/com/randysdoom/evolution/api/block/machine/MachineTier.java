package com.randysdoom.evolution.api.block.machine;

import com.randysdoom.evolution.api.block.IVariant;
import net.minecraft.util.IStringSerializable;

public enum MachineTier implements IStringSerializable, IVariant
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

    public int getInt()
    {
        return this.tierID;
    }

    public String getName()
    {
        return this.tierName;
    }

}
