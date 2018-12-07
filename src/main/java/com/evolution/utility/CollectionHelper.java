package com.evolution.utility;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionHelper
{

    public static <C extends Collection> int getNextNullIndex(C collection) throws Exception
    {
        int output = -1;
        int currentLoc = 0;

        for(Object object : collection)
        {
            if(object == null)
            {
                output = currentLoc;
                break;
            }
            currentLoc++;
        }

        if(output != -1)
        {
            throw new Exception("Collection is full.");
        }
        else
        {
            return output;
        }
    }

    public static <T> Collection<T> combineAndRemoveDuplicates(Collection<T> collection1, Collection<T> collection2)
    {
        Collection<T> merged;

        try
        {
            merged = collection1.getClass().newInstance();
        }
        catch(Exception ex)
        {
            merged = new ArrayList<T>();
        }

        merged.addAll(collection1);
        merged.addAll(collection2);

        Collection<T> output;

        try
        {
            output = collection1.getClass().newInstance();
        }
        catch(Exception ex)
        {
            output = new ArrayList<T>();
        }

        for(T val : merged)
        {
            if(!output.contains(val))
            {
                output.add(val);
            }
        }

        return output;
    }

}
