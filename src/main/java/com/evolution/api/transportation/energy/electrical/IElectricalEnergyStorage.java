package com.evolution.api.transportation.energy.electrical;

public interface IElectricalEnergyStorage
{

    int getEnergyStored();

    void setEnergyStored(int value);

    int getMaxEnergyStored();

    float getCurrent();

    float getMaxCurrent();

    float getResistance();

    void setResistance(float value);

    float getBaseEfficency();

    void setBaseEfficency(float value);

    boolean canStoreEnergy();

}
