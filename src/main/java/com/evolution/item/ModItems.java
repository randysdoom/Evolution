package com.evolution.item;

import com.evolution.item.base.ModItem;
import com.evolution.item.material.ItemDust;
import com.evolution.item.material.ItemIngot;
import com.evolution.reference.ModInfo;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public enum ModItems
{

    IN_GAME_DOC(new InGameDocumentation()),
    DEBUGGER(new Debugger()),
    TINDER(new Tinder()),
    DUST(new ItemDust()),
    INGOT(new ItemIngot());

    private ModItem item = null;

    ModItems(ModItem item){ this.item = item; }

    public ModItem getItem(){ return this.item; }

    public String getName(){ return this.getItem().getRegistryName().toString(); }

    @Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
    static class RegisterBlocks
    {

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event)
        {
            final IForgeRegistry<Item> registry = event.getRegistry();
            for(ModItems item : values())
            {
                if(item.getItem().shouldRegister())
                {
                    registry.register(item.getItem());
                }
            }
        }

        @SubscribeEvent
        public static void registerModels(final ModelRegistryEvent event)
        {
            for(ModItems item : values())
            {
                item.getItem().registerItemModels();
            }
        }


    }
    public static void registerModels()
    {
        for(ModItems item : ModItems.values())
        {
            item.getItem().registerItemModels();
        }
    }



}
