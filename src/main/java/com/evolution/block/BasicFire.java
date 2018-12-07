package com.evolution.block;

import com.evolution.block.base.ModBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BasicFire extends ModBlock
{

    private static final PropertyBool IS_LIT = PropertyBool.create("lit");

    public BasicFire()
    {
        super(Material.WOOD, "basic_fire");
        this.setDefaultState(blockState.getBaseState().withProperty(IS_LIT, false));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, IS_LIT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(IS_LIT, meta != 0);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(IS_LIT) ? 1 : 0;
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        //TODO: FIRE AND SMOKE PARTICLES BASED ON "LIT" VALUE
        if(stateIn.getValue(IS_LIT))
        {

        }
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

//    @Override
//    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
//    {
//        if(!worldIn.isRemote)
//        {
//            if(playerIn != null)
//            {
//                if(playerIn.isSneaking())
//                {
//                    switch(playerIn.getActiveItemStack().getItem().getRegistryName().getResourcePath())
//                    {
//                        case "flint_and_steel":
//                        {
//                            worldIn.setBlockState(pos, state.withProperty(IS_LIT, true), 3);
//                        }
//                        case "tinder":
//                        {
//                            worldIn.setBlockState(pos, state.withProperty(IS_LIT, true), 3);
//                        }
//                        default:
//                        {
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }

}
