package com.evolution.api.transportation.energy.electrical;

import net.minecraft.util.EnumFacing;

public interface IElectricalEnergyConsumer extends IElectricalEnergyStorage
{

    float getMaxEnergyCurrent();

    boolean canConsumeEnergy();

    boolean canRecieveEnergy();

    boolean canRecieveEnergy(EnumFacing direction);

}
