package com.evolution.block;

import com.evolution.EvolutionMod;
import com.evolution.block.base.ModBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CircuitBench extends ModBlock implements ITileEntityProvider
{

    public CircuitBench()
    {
        super(Material.IRON, "circuit_bench");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileCircuitBench();
    }

//    @Override
//    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
//    {
//        if(!worldIn.isRemote){ playerIn.openGui(EvolutionMod.INSTANCE, Handler.Gui.CIRCUIT_BENCH_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ()); }
//        return true;
//    }

    public static class TileCircuitBench extends TileEntity implements ISidedInventory
    {

        @Override
        public void setInventorySlotContents(int index, ItemStack stack){ }
        @Override
        public void openInventory(EntityPlayer player){}

        @Override
        public void closeInventory(EntityPlayer player){}

        @Override
        public void setField(int id, int value){ }

        @Override
        public void clear(){ }

        @Override
        public int getSizeInventory(){ return 0; }

        @Override
        public int getInventoryStackLimit(){ return 0; }

        @Override
        public int[] getSlotsForFace(EnumFacing side)
        {
            if(side.equals(EnumFacing.NORTH) || side.equals(EnumFacing.SOUTH) || side.equals(EnumFacing.EAST) || side.equals(EnumFacing.WEST)){ return new int[]{}; }
            return new int[]{0};
        }

        @Override
        public int getField(int id){ return 0; }

        @Override
        public int getFieldCount(){ return 0; }

        @Override
        public String getName(){ return I18n.format("tile.circuit_bench.name"); }

        @Override
        public ItemStack getStackInSlot(int index){ return null; }

        @Override
        public ItemStack decrStackSize(int index, int count){ return null; }

        @Override
        public ItemStack removeStackFromSlot(int index){ return null; }

        @Override
        public boolean hasCustomName(){ return true; }

        @Override
        public boolean isEmpty(){ return false; }

        @Override
        public boolean isUsableByPlayer(EntityPlayer player){ return false; }

        @Override
        public boolean isItemValidForSlot(int index, ItemStack stack){ return false; }

        @Override
        public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction){ return false; }

        @Override
        public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction){ return false; }

    }

}
