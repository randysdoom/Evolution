package com.evolution.asm;

import com.evolution.reference.ModInfo;
import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Arrays;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class EvolutionPlugin extends DummyModContainer implements IFMLLoadingPlugin
{
    private final ModMetadata meta = new ModMetadata();

    public EvolutionPlugin()
    {
        this.meta.modId = "evolution-asm";
        this.meta.name = "Evolution: ASM Module";
        this.meta.description = "Modifies various base classes that Evolution uses.";
        this.meta.version = ModInfo.MOD_VERSION;
        this.meta.authorList = Arrays.asList(new String[]{"Randysdoom"});
    }

    @Override
    public ModMetadata getMetadata(){ return this.meta; }

    @Override
    public String getModId(){ return this.meta.modId; }

    @Override
    public String getName(){ return this.meta.name; }

    @Override
    public String getVersion(){ return this.meta.version; }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller){ return true; }

    @Override
    public String[] getASMTransformerClass()
    {
        String[] strArray =
        {
//            GuiContainerClassTransformer.class.getName()
        };
        return strArray;
    }

    @Override
    public String getModContainerClass(){ return this.getClass().getName(); }

    @Nullable
    @Override
    public String getSetupClass(){ return null; }

    @Override
    public void injectData(Map<String, Object> data)
    {
        File MC_DIR = (File) data.get("mcLocation");

        boolean isCCCInstalled = false;
        boolean isCCLInstalled = false;

        if(new File(MC_DIR, "mods").exists())
        {
            File MODS_DIR = new File(MC_DIR, "mods");

            for(File file : MODS_DIR.listFiles())
            {
                if(!isCCCInstalled && !isCCLInstalled)
                {
                    if(file.getName().endsWith(".jar"))
                    {
                        if(file.getName().contains("CodeChickenLib")){ isCCLInstalled = true; }
                    }
                }else{ break; }
            }
        }
    }

    @Override
    public String getAccessTransformerClass(){ return null; }

}
