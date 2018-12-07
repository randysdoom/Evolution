package com.evolution.utility;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;

public class EnumFacingHelper
{

    public static EnumFacing getFromPlayerVector(Vec3d vec)
    {
        return EnumFacing.getFacingFromVector((float) vec.x, (float) vec.y, (float) vec.z);
    }

}
