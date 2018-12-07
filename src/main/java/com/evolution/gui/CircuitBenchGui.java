package com.evolution.gui;

import com.evolution.block.CircuitBench;
import com.evolution.container.CircuitBenchContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

public class CircuitBenchGui extends GuiContainer
{

    public CircuitBenchGui(EntityPlayer player, CircuitBench.TileCircuitBench bench)
    {
        super(new CircuitBenchContainer(player, bench));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {

    }

}
