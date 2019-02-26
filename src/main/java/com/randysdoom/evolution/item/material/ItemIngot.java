package com.randysdoom.evolution.item.material;

import com.randysdoom.evolution.item.base.ModItem;
import net.minecraft.item.Item;

public class ItemIngot extends ModItem
{

    public ItemIngot(String material)
    {
        super(new Item.Properties(), "material/ingot/" + material);
    }

}
