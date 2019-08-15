package com.randysdoom.evolution.handler.events;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockEventHandler
{

    @SubscribeEvent
    public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event)
    {
        IWorld world = event.getWorld();
        BlockPos pos = event.getPos();
        BlockState block = event.getState();
        Entity entity = event.getEntity();

        if (entity instanceof PlayerEntity)
            if (entity instanceof ServerPlayerEntity)
            {

            }

    }

    @SubscribeEvent
    public static void onBlockRightClick(PlayerInteractEvent.RightClickBlock event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Block block = world.getBlockState(pos).getBlock();
        PlayerEntity player = event.getEntityPlayer();

        if (player instanceof ServerPlayerEntity && event.getHand() == Hand.MAIN_HAND && !block.equals(Blocks.AIR))
        {
            if (block.equals(Blocks.GRAVEL))
            {
                if(player.isSneaking())
                {
                    player.sendMessage(new TranslationTextComponent("You attempt to sift through this block of gravel."));
                    if(world.rand.nextFloat() <= 0.1)
                    {
                        player.sendMessage(new TranslationTextComponent("You managed to find a piece of flint."));
                        world.addEntity(new ItemEntity(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.FLINT, 1)));
                    }
                    if(world.rand.nextFloat() <= 0.075)
                    {
                        player.sendMessage(new TranslationTextComponent("You have ran out of material to sift through."));
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                    }
                }
            }

        }
    }

}
