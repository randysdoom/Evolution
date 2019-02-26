package com.randysdoom.evolution.block;

import com.randysdoom.evolution.block.base.BlockMod;
import net.minecraft.block.material.Material;

public class OreBlock extends BlockMod
{

    public OreBlock(String dimension, String oreType)
    {
        super(Material.ROCK, String.format("ore/%s/%s", dimension, oreType));
    }

    //TODO: WORK ON ORE GENERATION

}
