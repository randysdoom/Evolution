package com.evolution.block;

import com.evolution.block.base.ModBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import static com.evolution.reference.ModInfo.MOD_ID;

@SuppressWarnings({"WeakerAccess", "NullableProblems"})
public class Ore extends ModBlock
{

    private OreType[] validTypes;
    private static final PropertyEnum<OreType> ORE_TYPE = PropertyEnum.create("ore", OreType.class);
    private final String dimensionName;

    public Ore(String dimension)
    {
        this(dimension, OreType.values());
    }

    public Ore(String dimension, OreType... validTypes)
    {
        super(Material.ROCK, "ore/" + dimension);
        this.dimensionName = dimension;
        this.validTypes = validTypes.length > 0 ? validTypes : OreType.values();
    }

    public String getUnlocalizedName(int meta){ return "ore." + validTypes[meta].getName() + "." + this.dimensionName + ".name"; }

    @Override
    protected BlockStateContainer createBlockState(){ return new BlockStateContainer(this, ORE_TYPE); }

    @Override
    public IBlockState getStateFromMeta(int meta){ return getDefaultState().withProperty(ORE_TYPE, validTypes[meta]); }

    @Override
    public int getMetaFromState(IBlockState state){ return OreType.getMetaFromType(state.getValue(ORE_TYPE), validTypes); }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(OreType type : validTypes)
        {
            items.add(new ItemStack(this, 1, OreType.getMetaFromType(type, validTypes)));
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, getMetaFromState(state));
    }

    public static class OreItem extends ItemMultiTexture
    {
        private Ore ore;

        public OreItem(Ore ore)
        {
            super(ore, ore, var1 -> OreType.getTypeFromMeta(var1.getMetadata(), ore.validTypes).getName());
            this.ore = ore;
            this.setRegistryName(ore.getRegistryName());
            this.setHasSubtypes(true);
        }

        @Override
        public String getItemStackDisplayName(ItemStack stack)
        {
            return I18n.format(ore.getUnlocalizedName(stack.getMetadata()));
        }

        @Override
        public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
        {
            return super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState.withProperty(ORE_TYPE, OreType.getTypeFromMeta(stack.getMetadata(), ore.validTypes)));
        }

        @Override
        public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
        {
            for(OreType type : ore.validTypes)
            {
                items.add(new ItemStack(this, 1, OreType.getMetaFromType(type, ore.validTypes)));
            }
        }

        public void registerItemModels()
        {
            for(OreType type : ore.validTypes)
            {
                String name = String.format("ore/%s/%s", ore.dimensionName, type.getName());
                ModelLoader.setCustomModelResourceLocation(this, OreType.getMetaFromType(type, ore.validTypes), new ModelResourceLocation(new ResourceLocation(MOD_ID, name), "inventory"));
            }
        }

    }

    public enum OreType implements IStringSerializable
    {

        COPPER("copper"),
        TIN("tin"),
        LEAD("lead"),
        SILVER("silver"),
        NICKEL("nickel");

        private String typeName;

        OreType(String typeName)
        {
            this.typeName = typeName;
        }

        @Override
        public String getName()
        {
            return typeName;
        }

        public static OreType getTypeFromMeta(int meta, OreType[] validValues)
        {
            if(validValues == values() || validValues == null)
            {
                return values()[meta];
            }
            else
            {
                return validValues[meta];
            }
        }

        public static int getMetaFromType(OreType oreType, OreType[] validValues)
        {
            int meta = 0;
            for(OreType type : validValues){ if(type == oreType){ break; }else{ meta++; } }
            return meta;
        }

    }

    //TODO: WORK ON ORE GENERATION

}
