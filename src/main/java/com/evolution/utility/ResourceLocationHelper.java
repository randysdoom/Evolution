package com.evolution.utility;

import javafx.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Various methods to assist in the use of ResourceLocations {@link ResourceLocation}
 */
public class ResourceLocationHelper
{

    /**
     * Gets the {@link InputStream} for the given {@link ResourceLocation}
     * @param resLocation
     * @return An InputStream for the given ResourceLocation
     */
    private static InputStream getInputStream(ResourceLocation resLocation)
    {
        InputStream inputStream = null;
        try
        {
            inputStream = Minecraft.getMinecraft().getResourceManager().getResource(resLocation).getInputStream();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return inputStream;
    }

    /**
     * Finds the Height and Width of the given Image {@link ResourceLocation}
     * @param resLocation Image ResourceLocation
     * @return Pair<Integer, Integer>(height, width)
     */
    public static Pair<Integer, Integer> getImageDimensions(ResourceLocation resLocation)
    {
        try
        {
            BufferedImage bufferedImage = ImageIO.read(getInputStream(resLocation));
            return new Pair<>(bufferedImage.getHeight(), bufferedImage.getWidth());
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return new Pair<>(256, 256);
    }

    /**
     * Finds the scaled Height and Width of the given Image {@link ResourceLocation}
     * @param resLocation Image ResourceLocation
     * @return Pair<Integer, Integer>(height, width)
     */
    public static Pair<Float, Float> getImageScale(ResourceLocation resLocation, int squareResolution)
    {
        if(squareResolution == 0){ squareResolution = 256; }
        Pair<Integer, Integer> imageDimensions = getImageDimensions(resLocation);
        String stringScaleY = String.valueOf((imageDimensions.getKey() / squareResolution));
        String stringScaleX = String.valueOf((imageDimensions.getValue() / squareResolution));
        stringScaleY = stringScaleY.substring(0, stringScaleY.indexOf('.') + 2);
        stringScaleX = stringScaleX.substring(0, stringScaleX.indexOf('.') + 2);
        float scaleY = Float.valueOf(stringScaleY);
        float scaleX = Float.valueOf(stringScaleX);
        return new Pair<>(scaleY, scaleX);
    }

}
