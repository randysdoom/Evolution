package com.evolution.handler.events;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockEventHandler
{

    @SubscribeEvent
    public static void onBlockPlaced(BlockEvent.PlaceEvent event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState block = event.getPlacedBlock();
        EntityPlayer player = event.getPlayer();

    }

    @SubscribeEvent
    public static void onBlockRightClick(PlayerInteractEvent.RightClickBlock event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Block block = world.getBlockState(pos).getBlock();
        EntityPlayer player = event.getEntityPlayer();

        if(player instanceof EntityPlayerMP && event.getHand() == EnumHand.MAIN_HAND && !block.equals(Blocks.AIR))
        {
            if(block.equals(Blocks.DIRT))
            {
                if(player.isSneaking())
                {
                    player.sendMessage(new TextComponentTranslation("You attempt to sift through this block of dirt."));
                    if(world.rand.nextFloat() <= 0.1)
                    {
                        player.sendMessage(new TextComponentTranslation("You managed to find a piece of flint."));
                        world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.FLINT, 1)));
                    }
                    if(world.rand.nextFloat() <= 0.075)
                    {
                        player.sendMessage(new TextComponentTranslation("You have ran out of material to sift through."));
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                    }
                }
            }

        }
    }

}
