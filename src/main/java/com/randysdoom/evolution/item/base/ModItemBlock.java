package com.randysdoom.evolution.item.base;

import com.randysdoom.evolution.EvolutionMod;
import com.randysdoom.evolution.block.base.BlockMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class ModItemBlock extends ItemBlock
{

    public ModItemBlock(BlockMod block)
    {
        super(block, new Item.Properties().group(EvolutionMod.EVOLUTION_GROUP));
        this.setRegistryName(block.getRegistryName());
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack)
    {
        return new TextComponentTranslation("block." + this.getRegistryName().getPath().replace('/', '.') + ".name");
    }

}
