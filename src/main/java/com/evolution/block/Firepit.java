package com.evolution.block;

import com.evolution.block.base.ModBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;

public class Firepit extends ModBlock implements ITileEntityProvider
{
    public static final PropertyInteger PROGRESS = PropertyInteger.create("progress", 0, 15);
    public static final PropertyBool BURNING = PropertyBool.create("burning");
    public static final PropertyBool BURNED = PropertyBool.create("burned");

    public Firepit(){ super(Material.WOOD, "firepit"); }

    @Override
    protected BlockStateContainer createBlockState(){ return new BlockStateContainer(this, PROGRESS, BURNING, BURNED); }

    @Override
    public int getMetaFromState(IBlockState state){ return state.getValue(PROGRESS); }

    @Override
    public IBlockState getStateFromMeta(int meta){ return getDefaultState().withProperty(PROGRESS, meta); }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return getDefaultState().withProperty(PROGRESS, 0).withProperty(BURNING, false).withProperty(BURNED, false);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        TileFirepit firepit = (TileFirepit) worldIn.getTileEntity(pos);
        if(firepit != null)
        {
            if(!playerIn.isSneaking())
            {

            }else
            {

            }
            return true;
        }
        return false;
    }

    @Override
    public void randomTick(World world, BlockPos pos, IBlockState state, Random random)
    {
        if(!isValidForPlacement(world, pos))
        {
            if(!world.isRemote){ world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this, 1, 0))); }
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos){ return isValidForPlacement(world, pos); }

    private boolean isValidForPlacement(World world, BlockPos pos)
    {
        System.out.println("Tried to place block at " + pos.toString());
        if(world.getBlockState(pos.offset(EnumFacing.NORTH)).getBlock() != Blocks.AIR)
            if(world.getBlockState(pos.offset(EnumFacing.EAST)).getBlock() != Blocks.AIR)
                if(world.getBlockState(pos.offset(EnumFacing.SOUTH)).getBlock() != Blocks.AIR)
                    if(world.getBlockState(pos.offset(EnumFacing.WEST)).getBlock() != Blocks.AIR)
                        return true;
        return false;
    }

    public boolean isComplete(IBlockState state)
    {
        return state.getValue(PROGRESS) == 15;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileFirepit();
    }

    public static class TileFirepit extends TileEntity implements ITickable, ISidedInventory
    {
        public Map<Integer, ItemStack> contents = new LinkedHashMap<>();

        @Override
        public void update()
        {

        }

        @Override
        public String getName()
        {
            return "evolution.entity.tile.firepit";
        }

        @Override
        public boolean hasCustomName()
        {
            return true;
        }

        @Override
        public int[] getSlotsForFace(EnumFacing side)
        {
            return new int[0];
        }

        @Override
        public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
        {
            return false;
        }

        @Override
        public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
        {
            if(Arrays.asList(getSlotsForFace(direction)).contains(index))
            {
                for(Map.Entry entry : contents.entrySet())
                {
                    if(entry.getKey().equals(index))
                    {
                        if(entry.getValue().equals(stack))
                        {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        @Override
        public int getSizeInventory()
        {
            return 0;
        }

        @Override
        public ItemStack getStackInSlot(int index)
        {
            if(!contents.isEmpty())
            {
                for(Map.Entry entry : contents.entrySet())
                {
                    if(entry.getKey().equals(index))
                    {
                        return (ItemStack) entry.getValue();
                    }
                }
            }
            return null;
        }

        @Override
        public ItemStack decrStackSize(int index, int count)
        {
            return null;
        }

        @Override
        public void setInventorySlotContents(int index, ItemStack stack)
        {
            contents.put(index, stack);
        }

        @Override
        public ItemStack removeStackFromSlot(int index)
        {
            return contents.remove(index);
        }

        @Override
        public int getInventoryStackLimit()
        {
            return 0;
        }

        @Override
        public boolean isUsableByPlayer(EntityPlayer player)
        {
            return false;
        }

        @Override
        public void openInventory(EntityPlayer player)
        {

        }

        @Override
        public void closeInventory(EntityPlayer player)
        {

        }

        @Override
        public boolean isItemValidForSlot(int index, ItemStack stack)
        {
            return false;
        }

        @Override
        public int getField(int id)
        {
            return 0;
        }

        @Override
        public void setField(int id, int value)
        {

        }

        @Override
        public int getFieldCount()
        {
            return 0;
        }

        @Override
        public void clear()
        {

        }

        @Override
        public boolean isEmpty()
        {
            return false;
        }
    }

}
