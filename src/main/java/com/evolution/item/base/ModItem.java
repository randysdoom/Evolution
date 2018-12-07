package com.evolution.item.base;

import com.evolution.reference.ModInfo;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ModItem extends Item
{
    private IStringSerializable[] variants = null;
    public String resourcePath = null;

    public ModItem(String registryName){ this(registryName, null); }

    public ModItem(String registryName, IStringSerializable[] variants){ this(new ResourceLocation(ModInfo.MOD_ID, registryName), variants); }

    public ModItem(ResourceLocation registryName, IStringSerializable[] variants)
    {
        super();
        this.setRegistryName(registryName);
        this.setUnlocalizedName(registryName.getResourcePath());
        this.variants = variants;
//        this.setCreativeTab(ModCreativeTabs.TAB_ITEMS);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){ return "item." + ModInfo.MOD_ID + "." + super.getUnlocalizedName().substring(5) + ".name"; }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        if(this.usesVariants()){
            String format = I18n.format(getUnlocalizedNameWithVariant(stack, variants));
            return format; }
        return I18n.format("item." + getUnlocalizedName(stack).substring(ModInfo.MOD_ID.length()+6));
    }

    private String getUnlocalizedNameWithVariant(ItemStack stack, IStringSerializable[] e)
    {
        String s = this.getUnlocalizedName() + "." + e[stack.getMetadata()].getName() + ".name";
        return s;
    }

    private boolean usesVariants(){ return variants != null; }

    public boolean shouldRegister(){ return true; }

    public void registerItemModels()
    {
        if(this.usesVariants() && resourcePath != null){ registerWithVariants(resourcePath); return; }
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    private ModelResourceLocation getModelResourceLocation(String prefix, String name)
    {
        return new ModelResourceLocation(new ResourceLocation("evolution", prefix != null ? prefix +"/" + name : name), "inventory");
    }

    public void registerWithVariants(String path)
    {
        for(int i = 0; i < variants.length; i++)
        {
            ModelLoader.setCustomModelResourceLocation(this, i, getModelResourceLocation(path, variants[i].getName()));
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(this.usesVariants()){ for( int i = 0; i < variants.length; i++){ items.add(new ItemStack(this, 1, i)); }}
        else super.getSubItems(tab, items);
    }

    public ItemStack getDefaultInstance(){ return new ItemStack(this, 1, 0); }

}
