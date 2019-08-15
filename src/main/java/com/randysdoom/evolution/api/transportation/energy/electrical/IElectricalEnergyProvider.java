package com.randysdoom.evolution.api.transportation.energy.electrical;

import net.minecraft.util.Direction;

public interface IElectricalEnergyProvider extends IElectricalEnergyStorage
{

    boolean canTransmitEnergy();

    boolean canTransmitEnergy(Direction direction);

}
