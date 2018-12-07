package com.evolution.proxy;

import com.evolution.EvolutionMod;
import com.evolution.ModConfiguration;
import com.evolution.handler.Handler;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy
{

    public void preInit(FMLPreInitializationEvent event)
    {
        ModConfiguration.init(event.getModConfigurationDirectory());

        Handler.registerObjects();
        Handler.registerEvents();
        Handler.ModKeybinds.registerKeybindings();
    }

    public void init(FMLInitializationEvent event)
    {
        Handler.ModRecipes.registerRecipes();

        NetworkRegistry.INSTANCE.registerGuiHandler(EvolutionMod.INSTANCE, new Handler.Gui());
    }

    public void postInit(FMLPostInitializationEvent event){}

    public void onServerStarting(FMLServerStartingEvent event){}

    public void onServerStarted(FMLServerStartedEvent event){}

    public void onServerStopping(FMLServerStoppingEvent event){}

    public void onServerStopped(FMLServerStoppedEvent event){}

}
