package com.evolution.utility;

import com.evolution.ModConfiguration;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class RegistryHelper
{

    private static <M extends AbstractMap, T extends TileEntity, N> M getTileEntityRegistry(String mapToReturn)
    {
        switch(mapToReturn)
        {
            case "nameToTile":
            {
                Field field = TileEntity.class.getFields()[1];
                field.setAccessible(true);
                try{ return (M) field.get(new HashMap<N, T>()); }catch(IllegalAccessException e){ e.printStackTrace(); }
            }
            case "tileToName":
            {
                Field field = TileEntity.class.getFields()[2];
                field.setAccessible(true);
                try{ return (M) field.get(new HashMap<T, N>()); }catch(IllegalAccessException e){ e.printStackTrace(); }
            }
            default:{ return null; }
        }
    }

    public static TileEntity getTileEntity(ResourceLocation resLoc){ return getTileEntity(resLoc.toString()); }

    public static TileEntity getTileEntity(String resLoc)
    {
        Map<String, Class<? extends TileEntity>> map = getTileEntityRegistry("nameToTile");
        if(map.keySet().contains(resLoc))
        {
            try
            {
                return map.get(resLoc).newInstance();
            }
            catch(Exception ex)
            {
                if(ModConfiguration.Core.isDebugLoggingEnabled)
                {
                    LogHelper.log(Level.INFO, String.format("Could not find create instance of Tile Entity { Registry Name: %s }", resLoc.toString()));
                }
                ex.printStackTrace();
            }
        }
        return null;
    }

}
