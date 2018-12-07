package com.evolution.utility;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.io.InputStream;

public class ColorHelper
{

    public static Color getColorFromResourceLocation(ResourceLocation resLoc, boolean includeAlpha)
    {
        if(resLoc == null) return Color.WHITE;
        BufferedImage image = null;

        try
        {
            InputStream stream = Minecraft.getMinecraft().getResourceManager().getResource(resLoc).getInputStream();
            image = ImageIO.read(stream);
            stream.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

        if(image == null)
        {
            System.out.println(String.format("Provided an invalid Resource Location: { %s } to get Color from.", resLoc.toString()));
            return Color.WHITE;
        }

        int height = image.getHeight();
        int width = image.getWidth();
        int pixelCount = height * width;
        int[] pixels = new int[pixelCount];
        Raster raster = image.getRaster();
        raster.getPixels(0, 0, width, height, pixels);
        int red = 0;
        int green = 0;
        int blue = 0;
        int alpha = includeAlpha ? 0 : 255;

        if(includeAlpha)
        {
            for(int y = 0; y < height; y++)
                for(int x = 0; x < width; x++)
                {
                    int color = pixels[x + y * width];
                    red += (color >> 16) & 0xFF;
                    green += (color >> 8) & 0xFF;
                    blue += (color & 0xFF);
                    alpha += (color >>> 24);
                }
        }
        else
        {
            for(int y = 0; y < height; y++)
                for(int x = 0; x < width; x++)
                {
                    int color = pixels[x + y * width];
                    red += (color >> 16) & 0xFF;
                    green += (color >> 8) & 0xFF;
                    blue += (color & 0xFF);
                }
        }


        red = red / pixelCount;
        green = green / pixelCount;
        blue = blue / pixelCount;
        if(includeAlpha)
        {
            alpha = alpha / pixelCount;
        }

        System.out.println(String.format("Got Color: { R: %s, G: %s, B: %s, A: %s } from Resource Location: %s", red, green, blue, alpha, resLoc.toString()));
        return washColor(red, green, blue, includeAlpha ? alpha : alpha * pixelCount, pixelCount);
    }

    private static Color washColor(int red, int green, int blue, int alpha, int amountToWash)
    {
        return new Color(red / amountToWash, green / amountToWash, blue / amountToWash, alpha / amountToWash);
    }

}
