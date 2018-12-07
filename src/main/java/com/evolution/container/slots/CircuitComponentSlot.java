package com.evolution.container.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CircuitComponentSlot extends Slot
{

    public CircuitComponentSlot(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
//        if(stack.getItem().equals())
        //TODO: COME BACK TO THIS
        return false;
    }

}
