package com.randysdoom.evolution.registration;

import com.randysdoom.evolution.EvolutionMod;
import com.randysdoom.evolution.reference.Index.Block;
import com.randysdoom.evolution.reference.Index.Item;
import com.randysdoom.evolution.reference.Index.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class RegistrationHandler
{

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<net.minecraft.item.Item> event)
    {
        final IForgeRegistry<net.minecraft.item.Item> registry = event.getRegistry();
        for (Item val : Item.values())
        {
            registry.register(val.getItem());
            EvolutionMod.getLogger().debug(String.format("Item: \'%s\' has been registered.", val.getItem().getRegistryName()));
        }

        for (Block val : Block.values())
        {
            registry.register(val.getItemBlock());
            EvolutionMod.getLogger().debug(String.format("ItemBlock: \'%s\' has been registered.", val.getItemBlock().getRegistryName()));
        }
    }

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<net.minecraft.block.Block> event)
    {
        final IForgeRegistry<net.minecraft.block.Block> registry = event.getRegistry();

        for (Block val : Block.values())
        {
            registry.register(val.getBlock());
            EvolutionMod.getLogger().debug(String.format("Block: \'%s\' has been registered.", val.getBlock().getRegistryName()));
        }
    }

    @SubscribeEvent
    public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event)
    {
        final IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();

        for (TileEntity val : TileEntity.values())
        {
            registry.register(val.getType());
            EvolutionMod.getLogger().debug(String.format("TileEntity: \'%s\' has been registered.", val.getType().getRegistryName()));
        }
    }

}
