package com.randysdoom.evolution.api.transportation.energy.electrical;

import net.minecraft.util.Direction;

public interface IElectricalEnergyConsumer extends IElectricalEnergyStorage
{

    float getMaxEnergyCurrent();

    boolean canConsumeEnergy();

    boolean canRecieveEnergy();

    boolean canRecieveEnergy(Direction direction);

}
