package com.evolution.utility;

import com.google.common.base.CaseFormat;

public class StringHelper
{

    public static String append(String string1, String string2, String divider)
    {
        if(!divider.equals(""))
        {
            return string1 + divider + string2;
        }
        else
        {
            return string1 + string2;
        }
    }

    public static String convertFormat(CaseFormat currentFormat, CaseFormat newFormat, String stringToConvert) { return currentFormat.to(newFormat, stringToConvert); }

    public static String toTitleCase(String string) { return string.substring(0, 1).toUpperCase() + string.substring(1, string.length()); }

    public static boolean containsIgnoreCase(String string1, String string2)
    {
        string1 = string1.toLowerCase();
        string2 = string2.toLowerCase();
        return string1.contains(string2);
    }

}