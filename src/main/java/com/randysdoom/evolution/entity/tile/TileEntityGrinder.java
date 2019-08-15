package com.randysdoom.evolution.entity.tile;

import com.randysdoom.evolution.reference.Index;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityGrinder extends TileEntityMod implements IRecipeHolder, IRecipeHelperPopulator
{
    private ItemStackHandler grinderInv;
    private int grinderFuelTime;
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private boolean isGrinding;
    private IRecipe currentRecipe;

    public TileEntityGrinder()
    {
        super(Index.TileEntity.MACHINE_GRINDER.getType());
        grinderInv = new ItemStackHandler(3);
    }

    @Override
    public void read(CompoundNBT compound)
    {
        grinderInv.deserializeNBT(compound.getCompound("inventory"));
        CompoundNBT fields = compound.getCompound("fields");
        this.setField(0, fields.getInt("grinderFuelTime"));
        this.setField(1, fields.getInt("currentItemBurnTime"));
        this.setField(2, fields.getInt("cookTime"));
        this.setField(3, fields.getInt("totalCookTime"));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound.put("inventory", grinderInv.serializeNBT());
        CompoundNBT fields = new CompoundNBT();
        fields.putInt("grinderFuelTime", this.getField(0));
        fields.putInt("currentItemBurnTime", this.getField(1));
        fields.putInt("cookTime", this.getField(2));
        fields.putInt("totalCookTime", this.getField(3));
        compound.put("fields", fields);
        return super.write(compound);
    }

    @Override
    public void fillStackedContents(RecipeItemHelper helper)
    {

    }

    @Override
    public void setRecipeUsed(@Nullable IRecipe<?> recipe)
    {

    }

    @Nullable
    @Override
    public IRecipe<?> getRecipeUsed()
    {
        return null;
    }

    public ItemStackHandler getGrinderInv()
    {
        return this.grinderInv;
    }
}
