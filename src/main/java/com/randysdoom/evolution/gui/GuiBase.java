package com.randysdoom.evolution.gui;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.StringTextComponent;

public abstract class GuiBase<T extends Container> extends ContainerScreen
{

    public GuiBase(T container, PlayerInventory inventorySlotsIn, String name)
    {
        super(container, inventorySlotsIn, new StringTextComponent(name));
    }

}
