package com.randysdoom.evolution.item.material;

import com.randysdoom.evolution.item.base.ModItem;
import net.minecraft.item.Item;

public class ItemDust extends ModItem
{

    public ItemDust(String material)
    {
        super(new Item.Properties(), "material/dust/" + material);
    }

}
