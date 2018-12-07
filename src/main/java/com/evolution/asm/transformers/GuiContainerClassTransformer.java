package com.evolution.asm.transformers;

import net.minecraft.launchwrapper.IClassTransformer;

public class GuiContainerClassTransformer implements IClassTransformer
{

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
        final String[] correctPackages = new String[]{ "net.minecraft.", "com.evolution" };
        boolean isCorrectPackage = false;
        for(String pckg : correctPackages){ if(!isCorrectPackage){ if(name.startsWith(pckg) || transformedName.startsWith(pckg)){ isCorrectPackage = true; } } }
        if(!isCorrectPackage){ return basicClass; }else
        {
            if(name.equals("bie") || transformedName.contains("GuiContainer"))
            {
                System.out.println("[Evolution ASM Module]: About to patch Class: \"" + name + "\".");
                basicClass = patchClassInJar(name, basicClass, transformedName);
                System.out.println("[Evolution ASM Module]: Finished patching Class: \"" + name + "\".");
            }
        }
        return basicClass;
    }

    public byte[] patchClassInJar(String name, byte[] bytes, String transformedName)
    {
        return bytes;
    }

}
