package com.randysdoom.evolution.gui;

import net.minecraft.client.gui.screen.inventory.FurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.util.text.StringTextComponent;

public class GuiGrinder extends FurnaceScreen
{

    public GuiGrinder(FurnaceContainer container, PlayerInventory inventory)
    {
        super(container, inventory, new StringTextComponent("Grinder"));
    }

}
