package com.randysdoom.evolution.api.transportation.energy.kinetic;

import net.minecraft.util.Direction;

public interface IKineticEnergyProvider extends IKineticEnergyStorage
{

    void transmitEnergy();

    void transmitEnergy(Direction direction);

    boolean canProvideEnergy();

    boolean canProvideEnergy(Direction direction);

}
