package com.evolution.api.transportation.energy.kinetic;

import net.minecraft.util.EnumFacing;

public interface IKineticEnergyProvider extends IKineticEnergyStorage
{

    void transmitEnergy();

    void transmitEnergy(EnumFacing direction);

    boolean canProvideEnergy();

    boolean canProvideEnergy(EnumFacing direction);

}
