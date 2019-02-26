package com.randysdoom.evolution.api.transportation.energy.kinetic;

public interface IKineticEnergyStorage
{

    int getEnergyStored();

    void setEnergyStored(int value);

    int getMaxEnergyStored();

    float getEfficency();

    void setEfficency(float value);

    boolean canStoreEnergy();

}
