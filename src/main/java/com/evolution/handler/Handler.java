package com.evolution.handler;

import com.evolution.EvolutionMod;
import com.evolution.gui.CircuitBenchGui;
import com.evolution.gui.PlayerAttributesGui;
import com.evolution.ModConfiguration;
import com.evolution.block.*;
import com.evolution.block.machine.MachineCasing;
import com.evolution.block.substitutions.Dirt;
import com.evolution.capability.player.IAttributes;
import com.evolution.item.*;
import com.evolution.container.CircuitBenchContainer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.lwjgl.input.Keyboard;

import static com.evolution.reference.ModInfo.MOD_ID;

@SuppressWarnings({"unused", "unchecked", "WeakerAccess"})
@GameRegistry.ObjectHolder(MOD_ID)
public class Handler
{

    public static class ModCapabilities
    {

        @CapabilityInject(IAttributes.class)
        public static Capability<IAttributes> ATTRIBUTES = null;

        static void registerCapabilities()
        {
            CapabilityManager.INSTANCE.register(IAttributes.class, new IAttributes.Storage(), IAttributes.Impl.class);
        }

        @SubscribeEvent
        public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event)
        {
            if(event.getObject() instanceof EntityPlayer)
            {
                event.addCapability(new ResourceLocation(MOD_ID, "player.attributes"), new IAttributes.Provider());
            }
        }

    }

    public static class ModBlocks
    {
//        public static MachineCasing MACHINECASING = new MachineCasing();
//        public static CompostBin COMPOSTBIN = new CompostBin();
//        public static Bonfire BONFIRE = new Bonfire();
//        public static Firepit PITFIRE = new Firepit();
//        public static Ore OVERWORLD_ORES = new Ore("overworld");
//        public static Ore NETHER_ORES = new Ore("nether");

//        static void registerBlocks()
//        {
//            GameRegistry.register(MACHINECASING);
//            GameRegistry.register(COMPOSTBIN);
//            GameRegistry.register(BONFIRE);
//            GameRegistry.register(PITFIRE);
//            GameRegistry.register(OVERWORLD_ORES);
//            GameRegistry.register(NETHER_ORES);
//        }
//
//        static void registerModels()
//        {
//            MACHINECASING.registerBlockModels();
//            COMPOSTBIN.registerBlockModels();
//            BONFIRE.registerBlockModels();
//            PITFIRE.registerBlockModels();
//        }

    }

    public static class ModBlockSubstitutions
    {

        public static Dirt DIRT;

//        private static void assignBlockSubstitutions()
//        {
//            DIRT = new Dirt();
//        }

//        static void registerBlockSubstitutions()
        {
//            assignBlockSubstitutions();
//            try
//            {
//                GameRegistry.addSubstitutionAlias("minecraft:dirt", GameRegistry.Type.BLOCK, DIRT);
//            }catch(ExistingSubstitutionException ex){ ex.printStackTrace(); }
        }

//        static void registerSubstitutionModels()
//        {
//            DIRT.registerBlockModels();
//        }

    }

    public static class ModItems
    {
        public static InGameDocumentation DOCUMENTATION;
        public static Debugger DEBUGGER;
        public static Tinder TINDER;

        public static MachineCasing.MachineCasingItem IBLOCK_MACHINECASING;
        public static CompostBin.CompostBinItem IBLOCK_COMPOSTBIN;
//        public static ModItemBlock IBLOCK_BONFIRE;
//        public static ModItemBlock IBLOCK_PITFIRE;
        public static Ore.OreItem IBLOCK_OVERWORLD_ORES;
        public static Ore.OreItem IBLOCK_NETHER_ORES;

        private static void assignItems()
        {
            TINDER = new Tinder();
            DOCUMENTATION = new InGameDocumentation();
            DEBUGGER = new Debugger();

//            IBLOCK_MACHINECASING = new MachineCasing.MachineCasingItem(MACHINECASING);
//            IBLOCK_COMPOSTBIN = new CompostBin.CompostBinItem(COMPOSTBIN);
//            IBLOCK_BONFIRE = new ModItemBlock(BONFIRE);
//            IBLOCK_PITFIRE = new ModItemBlock(PITFIRE);
//            IBLOCK_OVERWORLD_ORES = new Ore.OreItem(OVERWORLD_ORES);
//            IBLOCK_NETHER_ORES = new Ore.OreItem(NETHER_ORES);
        }

        static void registerModels()
        {

        }

    }

    public static class ModItemSubstitutions
    {

        private static void assignItemSubstitutions()
        {

        }

        static void registerItemSubstitutions()
        {
//            try
//            {
//
//            }catch(ExistingSubstitutionException ex){ ex.printStackTrace(); }
        }

        static void registerSubstitutionModels()
        {

        }
    }

    public static class ModTileEntities
    {

        static void registerTileEntities()
        {
            GameRegistry.registerTileEntity(CompostBin.TileCompostBin.class, "evolution:compost_bin");
            GameRegistry.registerTileEntity(Firepit.TileFirepit.class, "evolution:firepit");
        }

        static void bindTileEntityRenderers()
        {
//            ClientRegistry.bindTileEntitySpecialRenderer(CompostBin.TileCompostBin.class, new CompostBin.Renderer());
        }

    }

    public static class ModRecipes
    {

        private static void registerVanillaCraftingRecipes()
        {
//            addShapelessRecipe(new ItemStack(Blocks.PLANKS, 4, 0), getItem(Blocks.LOG, 0));
//            addShapelessRecipe(new ItemStack(Blocks.PLANKS, 4, 1), getItem(Blocks.LOG, 1));
//            addShapelessRecipe(new ItemStack(Blocks.PLANKS, 4, 2), getItem(Blocks.LOG, 2));
//            addShapelessRecipe(new ItemStack(Blocks.PLANKS, 4, 3), getItem(Blocks.LOG, 3));
//            addShapelessRecipe(new ItemStack(Blocks.PLANKS, 4, 4), getItem(Blocks.LOG2, 0));
//            addShapelessRecipe(new ItemStack(Blocks.PLANKS, 4, 5), getItem(Blocks.LOG2, 1));
//
//            addShapedRecipe(new ItemStack(Blocks.CRAFTING_TABLE), "PP ", "PP ", "   ", 'P', getItem(Blocks.PLANKS, 4, OreDictionary.WILDCARD_VALUE));
//            addShapedRecipe(new ItemStack(Blocks.CHEST), "PPP", "P P", "PPP", 'P', new ItemStack(Blocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE));
//            addShapedRecipe(new ItemStack(Blocks.FURNACE), "CCC", "C C", "CCC", 'C', Blocks.COBBLESTONE);
//
//            addShapedRecipe(new ItemStack(Blocks.HOPPER), "ICI", "I I", " I ", 'C', Blocks.CHEST, 'I', Items.IRON_INGOT);
//            addShapedRecipe(new ItemStack(Blocks.DISPENSER), "CCC", "CBC", "CRC", 'C', Blocks.COBBLESTONE, 'B', Items.BOW, 'R', Items.REDSTONE);
//            addShapedRecipe(new ItemStack(Blocks.DROPPER), "CCC", "C C", "CRC", 'C', Blocks.COBBLESTONE, 'R', Items.REDSTONE);
//            addShapedRecipe(new ItemStack(Blocks.UNPOWERED_REPEATER), "   ", "TRT", "SSS", 'S', Blocks.STONE_SLAB, 'R', Items.REDSTONE, 'T', Blocks.REDSTONE_TORCH);
//            addShapedRecipe(new ItemStack(Blocks.PISTON), "PPP", "CIC", "CRC", 'C', Blocks.COBBLESTONE, 'I', Items.IRON_INGOT, 'R', Items.REDSTONE);
//            addRecipe(new ShapedOreRecipe(Blocks.PISTON, "PPP", "CIC", "CRC", 'P', "plankWood", 'C', Blocks.COBBLESTONE, 'I', Items.IRON_INGOT, 'R', Items.REDSTONE));
        }

        private static void registerVanillaFurnaceRecipes()
        {
            for(int meta = 0; meta < 3; meta++){ addSmelting(new ItemStack(Blocks.LOG, 1, meta), new ItemStack(Items.COAL, 1, 1)); }
            for(int meta = 0; meta < 1; meta++){ addSmelting(new ItemStack(Blocks.LOG2, 1, meta), new ItemStack(Items.COAL, 1, 1)); }

            addSmelting(Blocks.COBBLESTONE, new ItemStack(Blocks.STONE, 1));
            addSmelting(Blocks.NETHERRACK, new ItemStack(Items.NETHERBRICK, 1, 0));
            addSmelting(Blocks.IRON_ORE, new ItemStack(Items.IRON_INGOT, 1, 0));
            addSmelting(Blocks.GOLD_ORE, new ItemStack(Items.GOLD_INGOT, 1, 0));
        }

        private static void registerCraftingTableRecipes()
        {
//            addRecipe(new ShapedOreRecipe(new ResourceLocation("plankWood"), COMPOSTBIN, "P P", "P P", "PPP", 'P'));
        }

        private static void registerFurnaceRecipes()
        {

        }

        //MACHINE RECIPES HERE

        public static void registerRecipes()
        {
            if(ModConfiguration.Core.modifyVanillaRecipes)
            {
//                CraftingManager.getInstance().getRecipeList().clear();
                registerVanillaCraftingRecipes();
                FurnaceRecipes.instance().getSmeltingList().clear();
                registerVanillaFurnaceRecipes();
            }
            registerCraftingTableRecipes();
            registerFurnaceRecipes();
        }

        private static Item getItem(Block block){ return getItem(block, 1, 0); }

        private static Item getItem(Block block, int meta){ return getItem(block, 1, meta); }

        private static Item getItem(Block block, int quantity, int meta){ return new ItemStack(block, quantity, meta).getItem(); }

//        private static void addRecipe(IRecipe RECIPE){ GameRegistry.addRecipe(RECIPE); }

//        private static void addShapedRecipe(ItemStack OUTPUT, Object... INPUT){ GameRegistry.addShapedRecipe(OUTPUT, INPUT); }

//        private static void addShapelessRecipe(ItemStack OUTPUT, Object... INPUT){ GameRegistry.addShapelessRecipe(OUTPUT, INPUT); }

        private static void addSmelting(Block input, ItemStack output, float experience){ GameRegistry.addSmelting(input, output, experience); }

        private static void addSmelting(Block input, ItemStack output){ addSmelting(input, output, 0F); }

        private static void addSmelting(Item input, ItemStack output, float experience){ GameRegistry.addSmelting(input, output, experience); }

        private static void addSmelting(Item input, ItemStack output){ addSmelting(input, output, 0F); }

        private static void addSmelting(ItemStack input, ItemStack output, float experience){ GameRegistry.addSmelting(input, output, experience); }

        private static void addSmelting(ItemStack input, ItemStack output){ addSmelting(input, output, 0F); }

    }

    public static class ModKeybinds
    {
        public static KeyBinding OpenPlayerAttributes = new KeyBinding("open.attributes.keybind", Keyboard.KEY_P, "key.categories.evolution");
        public static KeyBinding PrintBlockNBT = new KeyBinding("log.block.nbt", Keyboard.KEY_L, "key.categories.evolution");

        public static void registerKeybindings()
        {
            ClientRegistry.registerKeyBinding(OpenPlayerAttributes);
            ClientRegistry.registerKeyBinding(PrintBlockNBT);
        }

        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent event)
        {
            final World world = Minecraft.getMinecraft().world;
            final EntityPlayer player = !world.isRemote ? (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity() : Minecraft.getMinecraft().getIntegratedServer().getPlayerList().getPlayers().get(0);
            final BlockPos pos = player != null ? player.getPosition() : BlockPos.ORIGIN;

            if(player != null)
            {
                if(GameSettings.isKeyDown(OpenPlayerAttributes))
                {
                    player.openGui(EvolutionMod.INSTANCE, 0, world, pos.getX(), pos.getY(), pos.getZ());
                }

                if(GameSettings.isKeyDown(PrintBlockNBT))
                {

                }
            }

        }
    }

    public static class Gui implements IGuiHandler
    {

        public static final int PLAYER_ATTRIBUTE_GUI = 0;
        public static final int CIRCUIT_BENCH_GUI = 1;

        @Override
        public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
        {
            switch(ID)
            {
                case PLAYER_ATTRIBUTE_GUI: { return new PlayerAttributesGui(player); }
                case CIRCUIT_BENCH_GUI: { return new CircuitBenchGui(player, (CircuitBench.TileCircuitBench) world.getTileEntity(new BlockPos(x, y, z))); }
                default: return null;
            }
        }

        @Override
        public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
        {
            switch(ID)
            {
                case CIRCUIT_BENCH_GUI: { return new CircuitBenchContainer(player, (CircuitBench.TileCircuitBench) world.getTileEntity(new BlockPos(x, y, z))); }
                default: return null;
            }
        }

    }

    public static void registerObjects()
    {
//        ModCapabilities.registerCapabilities();
//        ModBlocks.registerBlocks();
//        ModBlockSubstitutions.registerBlockSubstitutions();
//        ModItems.registerItems();
//        ModItemSubstitutions.registerItemSubstitutions();
//        ModTileEntities.registerTileEntities();
    }

    public static void registerModels()
    {
//        ModBlocks.registerModels();
//        ModBlockSubstitutions.registerSubstitutionModels();
//        ModItems.registerModels();
//        ModItemSubstitutions.registerSubstitutionModels();
//        ModTileEntities.bindTileEntityRenderers();
    }

    public static void registerEvents()
    {
        MinecraftForge.EVENT_BUS.register(ModConfiguration.class);
//        MinecraftForge.EVENT_BUS.register(Handler.ModCapabilities.class);
        MinecraftForge.EVENT_BUS.register(Handler.ModKeybinds.class);
    }

}
