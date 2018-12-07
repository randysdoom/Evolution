package com.evolution.proxy;

import com.evolution.command.Kill;
import com.evolution.command.ListPlayerCapabilities;
import net.minecraftforge.fml.common.event.*;

public class ServerProxy extends CommonProxy
{

    public void onServerStarting(FMLServerStartingEvent event)
    {
        super.onServerStarting(event);

        event.registerServerCommand(new Kill());
        event.registerServerCommand(new ListPlayerCapabilities());
    }

    public void onServerStarted(FMLServerStartedEvent event){ super.onServerStarted(event); }

    public void onServerStopping(FMLServerStoppingEvent event){ super.onServerStopping(event); }

    public void onServerStopped(FMLServerStoppedEvent event){ super.onServerStopped(event); }

}
