package com.randysdoom.evolution.api.transportation.fluid;

import net.minecraft.util.EnumFacing;

public interface IFluidProvider extends IFluidStorage
{

    void transferFluid();

    void transferFluid(EnumFacing direction);

    boolean canTransferFluid();

    boolean canTransferFluid(EnumFacing direction);

}
