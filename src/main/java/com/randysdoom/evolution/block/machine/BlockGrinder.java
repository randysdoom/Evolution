package com.randysdoom.evolution.block.machine;

import com.randysdoom.evolution.block.base.BlockMod;
import com.randysdoom.evolution.entity.tile.TileEntityGrinder;
import com.randysdoom.evolution.reference.Index;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockGrinder extends BlockMod
{

    public BlockGrinder()
    {
        super(Material.IRON, "machine/grinder");
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world)
    {
        return Index.TileEntity.MACHINE_GRINDER.getTileEntity();
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            TileEntity te = worldIn.getTileEntity(pos);
            if (player.isSneaking() && te instanceof TileEntityGrinder)
            {
                player.sendMessage(new TextComponentString("This IS the tile you are looking for..."));
            }
        }
        return true;
    }

}
