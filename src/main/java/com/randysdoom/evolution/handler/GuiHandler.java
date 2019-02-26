package com.randysdoom.evolution.handler;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GuiHandler
{

    public static GuiScreen openGui(FMLPlayMessages.OpenContainer openContainer)
    {
        switch (openContainer.getWindowId())
        {
            default:
                return null;
        }
    }

}
