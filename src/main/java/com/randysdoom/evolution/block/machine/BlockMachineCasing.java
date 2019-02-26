package com.randysdoom.evolution.block.machine;

import com.randysdoom.evolution.api.block.machine.MachineTier;
import com.randysdoom.evolution.block.base.BlockMod;
import net.minecraft.block.material.Material;

public class BlockMachineCasing extends BlockMod
{

    public BlockMachineCasing(MachineTier tier)
    {
        super(Material.IRON, "machine/casing/" + tier.getName());
    }

}
