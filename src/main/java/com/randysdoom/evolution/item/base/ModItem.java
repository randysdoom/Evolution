package com.randysdoom.evolution.item.base;

import com.randysdoom.evolution.EvolutionMod;
import com.randysdoom.evolution.reference.ModInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;

public class ModItem extends Item
{

    public ModItem(@Nonnull Item.Properties properties, String registryName)
    {
        this(properties, new ResourceLocation(ModInfo.MOD_ID, registryName));
    }

    public ModItem(@Nonnull Item.Properties properties, ResourceLocation registryName)
    {
        super(properties.group(EvolutionMod.EVOLUTION_GROUP));
        this.setRegistryName(registryName);
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack)
    {
        return new TextComponentTranslation("item." + this.getRegistryName().getPath().replace('/', '.') + ".name");
    }

}
