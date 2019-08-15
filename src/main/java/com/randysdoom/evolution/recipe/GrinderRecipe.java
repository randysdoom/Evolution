package com.randysdoom.evolution.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GrinderRecipe implements IRecipe
{

    public static final IRecipeType GRINDER_TYPE = IRecipeType.<GrinderRecipe>register("grinding");

    public GrinderRecipe(ItemStack input, ItemStack output)
    {

    }

    @Override
    public boolean matches(IInventory inv, World worldIn)
    {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv)
    {
        return null;
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return null;
    }

    @Override
    public ResourceLocation getId()
    {
        return null;
    }

    @Override
    public IRecipeType getType()
    {
        return GRINDER_TYPE;
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return null;
    }

}
