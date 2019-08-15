package com.randysdoom.evolution.item.base;

import com.randysdoom.evolution.EvolutionMod;
import com.randysdoom.evolution.reference.ModInformation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;

public class ModItem extends Item
{

    public ModItem(@Nonnull Properties properties, String registryName)
    {
        this(properties, new ResourceLocation(ModInformation.MOD_ID, registryName));
    }

    public ModItem(@Nonnull Properties properties, ResourceLocation registryName)
    {
        super(properties.group(EvolutionMod.EVOLUTION_GROUP));
        this.setRegistryName(registryName);
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack)
    {
        return new TranslationTextComponent("item." + this.getRegistryName().getPath().replace('/', '.') + ".name");
    }

}
