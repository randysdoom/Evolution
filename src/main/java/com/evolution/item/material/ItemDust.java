package com.evolution.item.material;

import com.evolution.item.base.ModItem;
import com.evolution.reference.Index.Material;
import net.minecraft.util.ResourceLocation;

public class ItemDust extends ModItem
{

    public ItemDust()
    {
        super(new ResourceLocation("evolution:dust"), Material.getMaterials());
        this.resourcePath = "dusts";
    }

}
