package com.evolution.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerAttributesGui extends GuiBase
{
    private EntityPlayer player = null;

    public PlayerAttributesGui(EntityPlayer player)
    {
        super(false);
        if(player != null){ this.player = player;}
        else{ this.player = Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityPlayer ? (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity() : null; }
    }

}
