package com.evolution.block.base;

import com.evolution.api.block.machine.MachineTier;
import com.evolution.item.base.ModItemBlock;
import com.evolution.reference.ModInfo;
import com.evolution.utility.StringHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class ModMachineBlock extends ModBlock
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing", Arrays.asList(EnumFacing.HORIZONTALS));
    public static final PropertyEnum<MachineTier> MACHINE_TIER = PropertyEnum.create("machine_tier", MachineTier.class);
    private MachineTier[] validTiers;

    public ModMachineBlock(Material blockMaterial, String registryName, MachineTier... validTiers)
    {
        this(blockMaterial, new ResourceLocation(ModInfo.MOD_ID, registryName), validTiers);
    }

    public ModMachineBlock(Material blockMaterial, ResourceLocation registryName, MachineTier... validTiers)
    {
        super(blockMaterial, registryName);
        this.validTiers = validTiers.length > 0 ? validTiers : MachineTier.values();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return Arrays.asList(validTiers).indexOf(state.getValue(MACHINE_TIER));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(MACHINE_TIER, validTiers[meta]).withProperty(FACING, EnumFacing.NORTH);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, MACHINE_TIER, FACING);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return getStateFromMeta(meta);
    }

    @Override
    public void registerBlockModels()
    {
        assert getRegistryName() != null;
        for(int meta = 0; meta < validTiers.length; meta++)
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), meta, new ModelResourceLocation(getRegistryName(), "normal"));
        }
    }

    public static class ItemBlockMachine extends ModItemBlock
    {

        public ItemBlockMachine(ModMachineBlock block)
        {
            super(block);
            this.setHasSubtypes(true);
        }

        @Override
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
        {
            tooltip.add("Machine Tier: " + StringHelper.toTitleCase(((ModMachineBlock) block).validTiers[stack.getMetadata()].getName()));
        }

        @Override
        public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
        {
            return super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, block.getStateFromMeta(stack.getMetadata()));
        }

    }

}
