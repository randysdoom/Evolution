package com.evolution.utility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class PlayerHelper
{

    public static int getPlayerQuantityItem(EntityPlayer player, @Nullable ItemStack matchStack)
    {
        if(matchStack != null && !matchStack.getItem().equals(null))
        {
            for(ItemStack invStack : player.inventory.mainInventory)
            {
                if(invStack.getItem().equals(matchStack.getItem()) && invStack.getMetadata() == matchStack.getMetadata())
                {
                    return invStack.getCount();
                }
            }
        }
        return 0;
    }

    public static boolean doesPlayerInventoryContain(EntityPlayer player, ItemStack matchStack, int removeCount)
    {
        boolean returnValue = false;
        for(ItemStack invStack : player.inventory.mainInventory)
        {
            if(invStack.isItemEqual(matchStack))
            {
                returnValue = true;
                if(removeCount > 0)
                {
                    player.inventory.clearMatchingItems(matchStack.getItem(), matchStack.getMetadata(), removeCount, null);
                }
            }
        }
        return returnValue;
    }

}
