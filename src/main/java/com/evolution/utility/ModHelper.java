package com.evolution.utility;

import net.minecraftforge.fml.common.Loader;

public class ModHelper
{

    public static boolean isAnyModLoaded(String... modIDs)
    {
        boolean output = false;
        for(String modID : modIDs)
        {
            if(output)
            {
                break;
            }
            output = Loader.isModLoaded(modID);
        }
        return output;
    }

}
