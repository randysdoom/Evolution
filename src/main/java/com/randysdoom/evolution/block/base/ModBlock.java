package com.randysdoom.evolution.block.base;

import com.randysdoom.evolution.item.base.ModItemBlock;
import com.randysdoom.evolution.reference.ModInformation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class ModBlock extends Block
{

    public ModBlock(Material blockMaterial, String registryName)
    {
        this(blockMaterial, new ResourceLocation(ModInformation.MOD_ID, registryName));
    }

    private ModBlock(Material blockMaterial, ResourceLocation registryName)
    {
        super(Properties.create(blockMaterial));
        this.setRegistryName(registryName);
    }

    public ModItemBlock getItemBlock()
    {
        return new ModItemBlock(this);
    }

    //TileEntity Stuff

    private TileEntity getTileEntity()
    {
        return null;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return this.getTileEntity() != null;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return this.getTileEntity();
    }

}
