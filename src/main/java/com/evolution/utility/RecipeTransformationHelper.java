package com.evolution.utility;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;

import javax.annotation.Nonnull;

public class RecipeTransformationHelper
{
/*
    public static JsonObject getJsonFromRecipe(@Nonnull IRecipe recipe)
    {
        JsonObject json = new JsonObject();

        if(recipe.getRecipeSize() > 0)
        {
            if(recipe instanceof ShapedRecipes)
            {
                for(int i = 0; i < recipe.getRecipeSize(); i++)
                {
                    json.add("input" + i, getJsonFromItemStack(((ShapedRecipes) recipe).recipeItems[i]));
                }
            }else
            {
                for(int i = 0; i < 9; i++)
                {
                    json.add("input" + i, getJsonFromItemStack(((ShapelessRecipes)recipe).recipeItems.get(i)));
                }
            }

            json.add("output", getJsonFromItemStack(recipe.getRecipeOutput()));
        }
        return json;
    }

    public static JsonObject getJsonFromItemStack(@Nonnull ItemStack stack)
    {
        JsonObject json = new JsonObject();
        json.add("item", new JsonPrimitive(stack.getItem().getRegistryName().toString()));
        json.add("meta", new JsonPrimitive(stack.getMetadata()));
        json.add("quantity", new JsonPrimitive(stack.stackSize));
        return json;
    }
*/
}
