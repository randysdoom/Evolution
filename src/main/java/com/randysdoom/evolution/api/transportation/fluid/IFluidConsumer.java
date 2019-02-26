package com.randysdoom.evolution.api.transportation.fluid;

import net.minecraft.util.EnumFacing;

public interface IFluidConsumer extends IFluidStorage
{

    float getMaxFluidFlow();

    boolean canConsumeFluid();

    boolean canRecieveFluid();

    boolean canRecieveFluid(EnumFacing direction);

}
