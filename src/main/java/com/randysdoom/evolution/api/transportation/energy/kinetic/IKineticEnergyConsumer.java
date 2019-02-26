package com.randysdoom.evolution.api.transportation.energy.kinetic;

import net.minecraft.util.EnumFacing;

public interface IKineticEnergyConsumer extends IKineticEnergyStorage
{

    boolean canConsumeEnergy();

    boolean canRecieveEnergy();

    boolean canRecieveEnergy(EnumFacing direction);

}
