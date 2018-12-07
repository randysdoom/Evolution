package com.evolution.block.substitutions;

import net.minecraft.block.*;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class Dirt extends BlockDirt
{
    public static final PropertyBool HAS_MATERIAL = PropertyBool.create("has_material");

    public Dirt()
    {
        this.setRegistryName("evolution:dirt");
        this.setUnlocalizedName(Blocks.DIRT.getUnlocalizedName().substring(5));
        this.setSoundType(SoundType.GROUND);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT, SNOWY, HAS_MATERIAL);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(playerIn.isSneaking())
        {
            if(!worldIn.isRemote)
            {
                if(state.getValue(VARIANT) == DirtType.DIRT)
                {
                    if(state.getValue(HAS_MATERIAL))
                    {
                        if(worldIn.rand.nextFloat() <= 0.1)
                        {
                            playerIn.sendMessage(new TextComponentTranslation("You managed to find a piece of flint."));
                            worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.FLINT, 1)));
                        }
                        if(worldIn.rand.nextFloat() <= 0.075)
                        {
                            playerIn.sendMessage(new TextComponentTranslation("You have ran out of material to sift through."));
                            worldIn.setBlockState(pos, state.withProperty(HAS_MATERIAL, false), 3);
                        }
                    }else
                    {
                        playerIn.sendMessage(new TextComponentTranslation("This block has no more siftable material."));
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return getDefaultState().withProperty(VARIANT, DirtType.byMetadata(meta)).withProperty(HAS_MATERIAL, true);
    }

    public void registerBlockModels()
    {
        ModelLoader.setCustomStateMapper(this, new StateMapperBase(){
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                switch(state.getValue(VARIANT))
                {
                    case DIRT: { return new ModelResourceLocation("evolution:dirt", "variant=dirt"); }
                    case COARSE_DIRT: { return new ModelResourceLocation("evolution:dirt", "variant=coarse_dirt"); }
                    case PODZOL:
                    {
                        if(!state.getValue(SNOWY)){ return new ModelResourceLocation("evolution:dirt", "snowy=false,variant=podzol"); }
                        else if(state.getValue(SNOWY)){ return new ModelResourceLocation("evolution:dirt", "snowy=true,variant=podzol"); }
                    }
                }
                return new ModelResourceLocation("evolution:dirt", "variant=dirt");
            }
        });
    }

}
