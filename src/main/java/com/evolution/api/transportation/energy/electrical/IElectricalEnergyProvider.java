package com.evolution.api.transportation.energy.electrical;

import net.minecraft.util.EnumFacing;

public interface IElectricalEnergyProvider extends IElectricalEnergyStorage
{

    boolean canTransmitEnergy();

    boolean canTransmitEnergy(EnumFacing direction);

}
