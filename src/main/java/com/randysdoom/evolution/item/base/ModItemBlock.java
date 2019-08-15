package com.randysdoom.evolution.item.base;

import com.randysdoom.evolution.EvolutionMod;
import com.randysdoom.evolution.block.base.ModBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ModItemBlock extends BlockItem
{

    public ModItemBlock(ModBlock block)
    {
        super(block, new Item.Properties().group(EvolutionMod.EVOLUTION_GROUP));
        this.setRegistryName(block.getRegistryName());
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack)
    {
        return new TranslationTextComponent("block." + this.getRegistryName().getPath().replace('/', '.') + ".name");
    }

}
