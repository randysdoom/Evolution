package com.randysdoom.evolution.item.material;

import com.randysdoom.evolution.item.base.ModItem;

public class ItemIngot extends ModItem
{

    public ItemIngot(String material)
    {
        super(new Properties(), "material/ingot/" + material);
    }

}
