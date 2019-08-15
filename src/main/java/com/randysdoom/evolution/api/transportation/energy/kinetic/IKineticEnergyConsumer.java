package com.randysdoom.evolution.api.transportation.energy.kinetic;

import net.minecraft.util.Direction;

public interface IKineticEnergyConsumer extends IKineticEnergyStorage
{

    boolean canConsumeEnergy();

    boolean canRecieveEnergy();

    boolean canRecieveEnergy(Direction direction);

}
