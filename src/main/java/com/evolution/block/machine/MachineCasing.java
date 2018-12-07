package com.evolution.block.machine;

import com.evolution.api.block.machine.MachineTier;
import com.evolution.block.base.ModMachineBlock;
import com.evolution.item.base.ModItemBlock;
import com.evolution.utility.StringHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nullable;
import java.util.List;

import static com.evolution.reference.ModInfo.MOD_ID;

public class MachineCasing extends ModMachineBlock
{

    public MachineCasing()
    {
        super(Material.IRON, "machine/casing");
    }

    @Override
    protected BlockStateContainer createBlockState(){ return new BlockStateContainer(this, MACHINE_TIER); }

    @Override
    public IBlockState getStateFromMeta(int meta){ return getDefaultState().withProperty(MACHINE_TIER, MachineTier.values()[meta]); }

    @Override
    public int getMetaFromState(IBlockState state){ return state.getValue(MACHINE_TIER).getTierID(); }

    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(state.getBlock(), 1, getMetaFromState(state));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(MachineTier tier : MachineTier.values())
        {
            items.add(new ItemStack(this, 1, tier.getTierID()));
        }
    }

    public static class MachineCasingItem extends ModItemBlock
    {

        public MachineCasingItem(MachineCasing blockCasing)
        {
            super(blockCasing);
            this.setHasSubtypes(true);
        }

        @Override
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
        {
            tooltip.add(String.format("Machine Tier: %s", StringHelper.toTitleCase(MachineTier.values()[stack.getMetadata()].getName())));
        }

        @Override
        public void registerItemModels()
        {
            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(new ResourceLocation(MOD_ID, "machine/casing/basic"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation(new ResourceLocation(MOD_ID, "machine/casing/intermediate"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(this, 2, new ModelResourceLocation(new ResourceLocation(MOD_ID, "machine/casing/advanced"), "inventory"));
            ModelLoader.setCustomModelResourceLocation(this, 3, new ModelResourceLocation(new ResourceLocation(MOD_ID, "machine/casing/complex"), "inventory"));
        }

        @Override
        public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
        {
            return super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, ((MachineCasing) block).getStateFromMeta(stack.getMetadata()));
        }

        @Override
        public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
        {
            for(MachineTier tier : MachineTier.values())
            {
                items.add(new ItemStack(this, 1, tier.getTierID()));
            }
        }
    }

}
