package com.randysdoom.evolution.api.transportation.fluid;

import net.minecraftforge.fluids.Fluid;

/*
\ A basic Fluid Storage interface, all values are in the form of mB or Milli-Buckets of fluid.
 */
public interface IFluidStorage
{

    int getMaxFluidStorable();

    void setMaxFluidStorable(int value);

    Fluid getCurrentFluid();

    void setCurrentFluid(Fluid value);

    int getFluidStored();

    void setFluidStored();

}
