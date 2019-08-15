package com.randysdoom.evolution.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonHelper
{

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static String gsonFormat(Object toFormat)
    {
        return GSON.toJson(toFormat);
    }

}
