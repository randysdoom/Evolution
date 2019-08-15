package com.randysdoom.evolution.block.machine;

import com.randysdoom.evolution.block.base.ModBlock;
import com.randysdoom.evolution.entity.tile.TileEntityGrinder;
import com.randysdoom.evolution.helper.JsonHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockGrinder extends ModBlock
{

    public BlockGrinder()
    {
        super(Material.IRON, "machine/grinder");
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new TileEntityGrinder();
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote && player instanceof ServerPlayerEntity)
        {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof TileEntityGrinder)
            {
                if (player.isSneaking())
                {
                    TileEntityGrinder teGrinder = (TileEntityGrinder) worldIn.getTileEntity(pos);
                    player.sendMessage(new StringTextComponent("This IS the tile you are looking for:"));
                    String string = String.format("Data:\n%s", JsonHelper.GSON.toJson(teGrinder.getGrinderInv().serializeNBT().toString()));
                    string = string.replace("{", "\n{\n").replace("}", "\n}").replace(",", ",\n");
                    string = string.replace("[", "\n    [\n").replace("]", "\n    ]");
                    player.sendMessage(new StringTextComponent(string));
                } else
                {
//                    NetworkHooks.openGui((ServerPlayerEntity) player, new TileEntityGrinder.InteractionObject((TileEntityGrinder) te), pos);
                }
            }
        }
        return true;
    }

}
