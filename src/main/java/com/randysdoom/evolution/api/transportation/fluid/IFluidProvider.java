package com.randysdoom.evolution.api.transportation.fluid;

import net.minecraft.util.Direction;

public interface IFluidProvider extends IFluidStorage
{

    void transferFluid();

    void transferFluid(Direction direction);

    boolean canTransferFluid();

    boolean canTransferFluid(Direction direction);

}
