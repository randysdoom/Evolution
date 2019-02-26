package com.randysdoom.evolution.reference;

import com.randysdoom.evolution.api.block.machine.MachineTier;
import com.randysdoom.evolution.block.OreBlock;
import com.randysdoom.evolution.block.base.BlockMod;
import com.randysdoom.evolution.block.machine.BlockGrinder;
import com.randysdoom.evolution.block.machine.BlockMachineCasing;
import com.randysdoom.evolution.entity.tile.TileEntityGrinder;
import com.randysdoom.evolution.entity.tile.TileEntityMod;
import com.randysdoom.evolution.item.InGameDocumentation;
import com.randysdoom.evolution.item.ItemDebugger;
import com.randysdoom.evolution.item.base.ModItem;
import com.randysdoom.evolution.item.base.ModItemBlock;
import com.randysdoom.evolution.item.material.ItemDust;
import com.randysdoom.evolution.item.material.ItemIngot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public class Index
{

    public enum Block
    {

        MACHINE_BLOCK_ADVANCED(new BlockMachineCasing(MachineTier.ADVANCED)),
        MACHINE_BLOCK_BASIC(new BlockMachineCasing(MachineTier.BASIC)),
        MACHINE_BLOCK_COMPLEX(new BlockMachineCasing(MachineTier.COMPLEX)),
        MACHINE_BLOCK_INTERMEDIATE(new BlockMachineCasing(MachineTier.INTERMEDIATE)),
        ORE_GRINDER(new BlockGrinder()),
        OVERWORLD_BAUXITE_ORE(new OreBlock("overworld", "bauxite")),
        OVERWORLD_COPPER_ORE(new OreBlock("overworld", "copper")),
        OVERWORLD_LEAD_ORE(new OreBlock("overworld", "lead")),
        OVERWORLD_NICKEL_ORE(new OreBlock("overworld", "nickel")),
        OVERWORLD_SILVER_ORE(new OreBlock("overworld", "silver")),
        OVERWORLD_TIN_ORE(new OreBlock("overworld", "tin")),
        OVERWORLD_ZINC_ORE(new OreBlock("overworld", "zinc"));

        private BlockMod block;

        Block(@Nonnull BlockMod block)
        {
            this.block = block;
        }

        public BlockMod getBlock()
        {
            return this.block;
        }

        public ModItemBlock getItemBlock()
        {
            return this.block.getItemBlock();
        }

        public static Stream<Block> stream()
        {
            return Stream.of(Block.values());
        }

    }

    public enum Item
    {

        DEBUGGER(new ItemDebugger()),
        DUST_BAUXITE(new ItemDust("bauxite")),
        DUST_BRONZE(new ItemDust("bronze")),
        DUST_CHARCOAL(new ItemDust("charcoal")),
        DUST_COAL(new ItemDust("coal")),
        DUST_COPPER(new ItemDust("copper")),
        DUST_DIAMOND(new ItemDust("diamond")),
        DUST_EMERALD(new ItemDust("emerald")),
        DUST_GOLD(new ItemDust("gold")),
        DUST_IRON(new ItemDust("iron")),
        DUST_LEAD(new ItemDust("lead")),
        DUST_SILVER(new ItemDust("silver")),
        DUST_STONE(new ItemDust("stone")),
        DUST_TIN(new ItemDust("tin")),
        DUST_ZINC(new ItemDust("zinc")),
        INGOT_COPPER(new ItemIngot("copper")),
        INGOT_LEAD(new ItemIngot("lead")),
        INGOT_SILVER(new ItemIngot("silver")),
        INGOT_TIN(new ItemIngot("tin")),
        IN_GAME_DOC(new InGameDocumentation());

        private ModItem item;

        Item(@Nonnull ModItem item)
        {
            this.item = item;
        }

        public ModItem getItem()
        {
            return this.item;
        }

        public ItemStack getDefaultInstance()
        {
            return this.getItem().getDefaultInstance();
        }

        public static Stream<Item> stream()
        {
            return Stream.of(Item.values());
        }

    }

    public enum TileEntity
    {

        MACHINE_GRINDER(TileEntityType.register("evolution:machine/grinder", TileEntityType.Builder.create(TileEntityGrinder::new)));

        private TileEntityType tet;
        private TileEntityMod te;

        TileEntity(TileEntityType<? extends TileEntityMod> tet)
        {
            this.tet = tet;
            this.te = tet.create();
        }

        public TileEntityMod getTileEntity()
        {
            return this.te;
        }

        public TileEntityType<?> getType()
        {
            return this.tet;
        }

        public ResourceLocation getName()
        {
            return this.tet.getRegistryName();
        }

    }

    public static class GUI
    {

        public enum Background
        {

        }

        public enum Object
        {

            ;

            Object()
            {
            }

        }

    }

}
