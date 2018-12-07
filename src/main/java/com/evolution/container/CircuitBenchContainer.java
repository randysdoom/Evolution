package com.evolution.container;

import com.evolution.block.CircuitBench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class CircuitBenchContainer extends Container
{

    public CircuitBenchContainer(EntityPlayer player, CircuitBench.TileCircuitBench bench)
    {

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn){ return true; }

}
