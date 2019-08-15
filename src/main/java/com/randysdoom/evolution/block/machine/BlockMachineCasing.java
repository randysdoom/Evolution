package com.randysdoom.evolution.block.machine;

import com.randysdoom.evolution.api.block.machine.MachineTier;
import com.randysdoom.evolution.block.base.ModBlock;
import net.minecraft.block.material.Material;

public class BlockMachineCasing extends ModBlock
{

    public BlockMachineCasing(MachineTier tier)
    {
        super(Material.IRON, "machine/casing/" + tier.getName());
    }

}
