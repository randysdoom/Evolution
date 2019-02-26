package com.randysdoom.evolution.api.transportation.energy.electrical;

public interface IElectricalEnergyStorage
{

    int getEnergyStored();

    void setEnergyStored(int value);

    int getMaxEnergyStored();

    float getCurrent();

    float getMaxCurrent();

    float getBaseEfficency();

    void setBaseEfficency(float value);

    boolean canStoreEnergy();

}
