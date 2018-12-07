package com.evolution.item;

import com.evolution.block.Firepit;
import com.evolution.item.base.ModItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

public class Tinder extends ModItem implements INBTSerializable<NBTTagCompound>
{

    private NBTTagCompound nbtCompound;
    private Material tinderMaterial;
    private int burnTimeTotal = tinderMaterial != null ? tinderMaterial.getTotalBurnTime() : Material.values()[0].getTotalBurnTime();
    private boolean isLit = false;
    private int burnTimeRemaining = burnTimeTotal;

    public Tinder()
    {
        super("tinder");
        this.addPropertyOverride(new ResourceLocation("tinder_material"), (stack, worldIn, entityIn) -> getMetadata(stack));
        this.setMaxStackSize(1);
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        if(nbtCompound == null)
        {
            nbtCompound = new NBTTagCompound();
        }

        if(!nbtCompound.hasKey("material") || (nbtCompound.hasKey("material") && nbtCompound.getString("material") != tinderMaterial.getName()))
        {
            nbtCompound.setString("material", tinderMaterial.getName());
        }

        if(!nbtCompound.hasKey("burn_time") || (nbtCompound.hasKey("burn_time") && nbtCompound.getInteger("burn_time") != tinderMaterial.getTotalBurnTime()))
        {
            nbtCompound.setInteger("burn_time", tinderMaterial.getTotalBurnTime());
        }

        return nbtCompound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbtCompoundIN)
    {
        if(nbtCompoundIN != null)
        {
            if(nbtCompound != null)
            {
                if(tinderMaterial == null && nbtCompound.hasKey("material"))
                {
                    tinderMaterial = Material.getFromName(nbtCompound.getString("material"));
                }
                else if(tinderMaterial != null && tinderMaterial != Material.getFromName(nbtCompound.getString("material")))
                {
                    tinderMaterial = Material.getFromName(nbtCompound.getString("material"));
                }


            }
            else
            {
                nbtCompound = nbtCompoundIN;

            }
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(stack.getItem() == this && worldIn != null)
        {
            if(isLit && (burnTimeRemaining <= burnTimeTotal) && (burnTimeRemaining > 0))
            {
                burnTimeRemaining--;
            }
            else if(burnTimeRemaining == 0)
            {
                stack.setCount(0);
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
//        if(!worldIn.isRemote)
//        {
//            BlockPos pos = worldIn.rayTraceBlocks(new Vec3d(playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ), playerIn.getLookVec()).getBlockPos();
//            IBlockState state = worldIn.getBlockState(pos);
//            if(state.getBlock() instanceof Firepit)
//            {
//                Firepit firepit = ((Firepit)state.getBlock());
//                if(firepit.isComplete(state))
//                {
//                    worldIn.setBlockState(pos, state.getBlock().getDefaultState().withProperty(Firepit.PROGRESS, 15).withProperty(Firepit.BURNING, true).withProperty(Firepit.BURNED, true), 3);
//                }
//            }
//        }
        if(!worldIn.isRemote){ playerIn.sendMessage(new TextComponentString("Right Click!")); }
        return ActionResult.newResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    private enum Material implements IStringSerializable
    {

        STICKS("sticks", 1200);

        private String name;
        private int burn_time;

        Material(String name, int burn_time)
        {
            this.name = name;
            this.burn_time = burn_time;
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        public int getTotalBurnTime()
        {
            return this.burn_time;
        }

        public static Material getFromName(String name)
        {
            for(Material mat : values())
            {
                if(mat.getName().equalsIgnoreCase(name))
                {
                    return mat;
                }
            }
            return values()[0];
        }

    }

}
