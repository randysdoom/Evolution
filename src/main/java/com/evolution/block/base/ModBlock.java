package com.evolution.block.base;

import com.evolution.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import static com.evolution.reference.ModInfo.MOD_ID;

public class ModBlock extends Block
{

    public ModBlock(Material blockMaterial, String registryName) { this(blockMaterial, new ResourceLocation(MOD_ID, registryName)); }

    public ModBlock(Material blockMaterial, ResourceLocation registryName)
    {
        super(blockMaterial);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(registryName.toString().replace(':', '.').replace('/', '.'));
//        this.setCreativeTab(ModCreativeTabs.TAB_BLOCKS);
    }

    public void registerBlockModels(){ ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory")); }

}
