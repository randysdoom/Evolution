package com.evolution.proxy;

import com.evolution.handler.Handler;
import com.evolution.reference.ModInfo;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.*;

public class ClientProxy extends CommonProxy
{

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);

        OBJLoader.INSTANCE.addDomain(ModInfo.MOD_ID);
        Handler.registerModels();
    }

    @Override
    public void init(FMLInitializationEvent event){ super.init(event); }

    @Override
    public void postInit(FMLPostInitializationEvent event){ super.postInit(event); }

}
