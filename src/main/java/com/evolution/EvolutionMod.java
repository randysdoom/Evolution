package com.evolution;

import com.evolution.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import static com.evolution.reference.ModInfo.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION)
public class EvolutionMod
{
    @Mod.Instance(MOD_ID)
    public static EvolutionMod INSTANCE;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){ proxy.preInit(event); }

    @EventHandler
    public void init(FMLInitializationEvent event){ proxy.init(event); }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){ proxy.postInit(event); }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event){ proxy.onServerStarting(event); }

    @EventHandler
    public void onServerStarted(FMLServerStartedEvent event){ proxy.onServerStarted(event); }

    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event){ proxy.onServerStopping(event); }

    @EventHandler
    public void onServerStopped(FMLServerStoppedEvent event){ proxy.onServerStopped(event); }

}
