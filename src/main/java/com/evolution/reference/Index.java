package com.evolution.reference;

import net.minecraft.util.IStringSerializable;

import java.io.File;

public class Index
{

    public static class Blocks
    {



    }

    public static class Items
    {

//        public static Tinder TINDER = Handler.Items.TINDER.getItem();
//        public static InGameDocumentation DOCUMENTATION = Handler.Items.DOCUMENTATION.getItem();
//        public static Debugger DEBUGGER = Handler.Items.DEBUGGER.getItem();
//        public static Dust DUST = Handler.ModItems.DUST.getItem();
//        public static Ingot INGOT = Handler.Items.INGOT.getItem();
//        public static ChemicalElements ELEMENTS = Handler.Items.ELEMENTS.getItem();

    }

    public static class Files
    {
        public static File CONFIG_DIRECTORY;
        public static File MODS_DIRECTORY;
        public static File LIBRARIES_DIRECTORY;
    }

    public enum Material implements IStringSerializable
    {

        STONE("stone"),
        CHARCOAL("charcoal"),
        COAL("coal"),
        COPPER("copper"),
        TIN("tin"),
        IRON("iron"),
        LEAD("lead"),
        SILVER("silver"),
        GOLD("gold"),
        DIAMOND("diamond"),
        EMERALD("emerald");

        private String materialName = null;

        Material(String materialName){ this.materialName = materialName; }

        @Override
        public String getName(){ return this.materialName; }

        public String getName(boolean capitalize)
        {
            if(capitalize)
            {
                return this.getName().substring(0, 1).toUpperCase() + this.getName().substring(1);
            }
            return this.materialName;
        }

        public static Material[] getMaterials(){ return values();}

        public static Material[] getMaterials(Material... materials){ return materials; }

    }

    public static class URLs
    {

    }

}
