package com.randysdoom.evolution;

import com.randysdoom.evolution.handler.GuiHandler;
import com.randysdoom.evolution.reference.Index;
import com.randysdoom.evolution.reference.ModInfo;
import com.randysdoom.evolution.registration.RegistrationHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value = ModInfo.MOD_ID)
public class EvolutionMod
{

    public EvolutionMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.register(RegistrationHandler.class);
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> GuiHandler::openGui);
    }

    public static final ItemGroup EVOLUTION_GROUP = new ItemGroup("Evolution")
    {
        @Override
        public ItemStack createIcon()
        {
            return Index.Item.DEBUGGER.getDefaultInstance();
        }
    };

    public static Logger getLogger()
    {
        return LogManager.getLogger("Evolution Mod");
    }

}
