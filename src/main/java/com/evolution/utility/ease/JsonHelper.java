package com.evolution.utility.ease;

import com.google.gson.*;

import java.io.*;
import java.net.URL;

public class JsonHelper
{

    public static JsonObject toJsonObject(String s)
    {
        JsonParser jp = new JsonParser();
        return jp.parse(s).getAsJsonObject();
    }

    public static JsonObject getJsonFromURL(URL u)
    {
        InputStream is;
        InputStreamReader isr;
        BufferedReader br;
        StringBuilder sb = new StringBuilder();

        try
        {
            is = u.openStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String ln;
            while((ln = br.readLine()) != null){ sb.append(ln); }
            is.close();
        }catch(Exception ex){ throw new RuntimeException(ex); }

        if(sb.length() > 0){ return toJsonObject(sb.toString()); }
        throw new RuntimeException(String.format("Could not read Json from URL: \'%s\', make sure the URL is correct, and that the URL actually points to a valid Json file.", u.toString()));
    }

    public static JsonObject getJsonFromFile(File f)
    {
        if(!FileHelper.isFileEmpty(f))
        {
            StringBuilder sb = new StringBuilder();
            try
            {
                BufferedReader br = FileHelper.Read.getBufferedReader(f);
                String ln;
                while((ln = br.readLine()) != null){ sb.append(ln); }
                br.close();
            }catch(Exception ex){ ex.printStackTrace(); }
            return toJsonObject(sb.toString());
        }
        throw new RuntimeException(String.format("Could not read Json from File: \'%s\', make sure the file name is correct, and that the file actually contains valid Json text.", f.getName()));
    }

    public static void writeJsonToFile(File f, JsonObject json)
    {
        FileHelper.Write.writeToFile(f, getGson(true).toJson(json), false);
    }

    public static String format(String s){ return format(toJsonObject(s), false); }

    public static String format(String s, boolean format){ return format(toJsonObject(s), format); }

    public static String format(JsonObject j){ return format(j, false); }

    public static String format(JsonObject j, boolean prettyPrint){ return getGson(prettyPrint).toJson(j); }

    public static Gson getGson(boolean prettyPrint)
    {
        GsonBuilder gb = new GsonBuilder();
        if(prettyPrint){ gb.setPrettyPrinting(); }
        return gb.create();
    }

    public static boolean getBoolean(JsonObject j, String name, boolean defVal)
    {
        return j.has(name) ? j.get(name).getAsBoolean() : defVal;
    }

    public static String getString(JsonObject j, String name, String defVal)
    {
        return j.has(name) ? j.get(name).getAsString() : defVal;
    }

    public static int getInt(JsonObject j, String name, int defVal)
    {
        return j.has(name) ? j.get(name).getAsInt() : defVal;
    }

    public static float getFloat(JsonObject j, String name, float defVal)
    {
        return j.has(name) ? j.get(name).getAsFloat() : defVal;
    }

    public static double getDouble(JsonObject j, String name, double defVal)
    {
        return j.has(name) ? j.get(name).getAsDouble() : defVal;
    }

}
