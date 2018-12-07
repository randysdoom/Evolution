package com.evolution.asm.transformers;

import net.minecraft.launchwrapper.IClassTransformer;

public class BasicClassTransformer implements IClassTransformer
{
    private Class classToTransform = null;

    public BasicClassTransformer(Class classToTransform){ this.classToTransform = classToTransform; }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
        return basicClass;
    }

}
