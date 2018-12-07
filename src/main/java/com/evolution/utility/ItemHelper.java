package com.evolution.utility;

import net.minecraft.nbt.NBTTagCompound;

public class ItemHelper
{

    public static boolean validateTool(NBTTagCompound tagCompound)
    {
        boolean returnValue = false;
        if(!tagCompound.getString("tool").equals(""))
        {

        }
        return returnValue;
    }

}
