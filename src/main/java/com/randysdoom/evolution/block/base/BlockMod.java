package com.randysdoom.evolution.block.base;

import com.randysdoom.evolution.item.base.ModItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

import static com.randysdoom.evolution.reference.ModInfo.MOD_ID;

public class BlockMod extends Block
{

    public BlockMod(Material blockMaterial, String registryName)
    {
        this(blockMaterial, new ResourceLocation(MOD_ID, registryName));
    }

    private BlockMod(Material blockMaterial, ResourceLocation registryName)
    {
        super(Block.Properties.create(blockMaterial));
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
    public boolean hasTileEntity(IBlockState state)
    {
        return this.getTileEntity() != null;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world)
    {
        return this.getTileEntity();
    }

}
