package com.evolution.item.base;

import com.evolution.ModCreativeTabs;
import com.evolution.block.base.ModBlock;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class ModItemBlock extends ItemBlock
{

    public ModItemBlock(ModBlock block)
    {
        super(block);
        this.setRegistryName(block.getRegistryName());
        this.setUnlocalizedName(block.getUnlocalizedName());
//        this.setCreativeTab(ModCreativeTabs.TAB_BLOCKS);
    }

    @Override
    public String getUnlocalizedName(){ return super.getUnlocalizedName().substring(5) + ".name"; }

    @Override
    public String getUnlocalizedName(ItemStack stack){ return getUnlocalizedName(); }

    @Override
    public String getItemStackDisplayName(ItemStack stack){ return I18n.format(getUnlocalizedName().substring(10)); }

    public void registerItemModels()
    {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
