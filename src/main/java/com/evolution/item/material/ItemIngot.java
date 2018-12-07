package com.evolution.item.material;

import com.evolution.item.base.ModItem;
import com.evolution.reference.Index.Material;
import net.minecraft.util.ResourceLocation;

public class ItemIngot extends ModItem
{

    public ItemIngot()
    {
        super(new ResourceLocation("evolution", "ingot"), Material.getMaterials(Material.COPPER, Material.TIN, Material.LEAD, Material.SILVER));
        this.resourcePath = "refined/ingots";
    }

}
