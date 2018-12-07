package com.evolution.item.base;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.*;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.*;

public class ModTool extends ItemTool
{

    public ModTool(ToolMaterial materialIn, String toolType)
    {
        super(materialIn, getEffectiveBlocks(toolType));
    }

    public ModTool(ToolMaterial materialIn, Set<Block> effectiveBlocks)
    {
        super(materialIn, effectiveBlocks);
    }

    @Override
    public String getUnlocalizedName(){ return super.getUnlocalizedName().substring(5) + ".name"; }

    @Override
    public String getUnlocalizedName(ItemStack stack){ return getUnlocalizedName(); }

    @Override
    public String getItemStackDisplayName(ItemStack stack){ return I18n.format(getUnlocalizedName().substring(10)); }

    static Set<Block> getEffectiveBlocks(String toolType)
    {
        Set<Block> blocks = new HashSet<>();
        try
        {
            switch(toolType)
            {
                case "pickaxe":
                {
                    blocks = (Set<Block>) ReflectionHelper.findField(ItemPickaxe.class, "EFFECTIVE_ON").get(blocks);
                }
                case "axe":
                {
                    blocks = (Set<Block>) ReflectionHelper.findField(ItemAxe.class, "EFFECTIVE_ON").get(blocks);
                }
                case "shovel":
                {
                    blocks = (Set<Block>) ReflectionHelper.findField(ItemSpade.class, "EFFECTIVE_ON").get(blocks);
                }
            }
        }
        catch(IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return blocks;
    }

    public void registerItemModels(){ assert getRegistryName() != null; ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory")); }

}
