package com.randysdoom.evolution;

import com.randysdoom.evolution.handler.events.BlockEventHandler;
import com.randysdoom.evolution.reference.Index;
import com.randysdoom.evolution.reference.ModInformation;
import com.randysdoom.evolution.registration.RegistrationHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value = ModInformation.MOD_ID)
public class EvolutionMod
{

    public EvolutionMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

//        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> (openContainer) -> {
//            TileEntityMod te = (TileEntityMod) Minecraft.getInstance().world.getTileEntity(openContainer.getAdditionalData().readBlockPos());
//            if(te != null)
//            {
//                switch (openContainer.getId().getPath())
//                {
//                    case "grinder": return new GuiGrinder(Minecraft.getInstance().player.inventory, (TileEntityGrinder) te);
//                    default: return null;
//                }
//            }
//            return null;
//        });

        modEventBus.register(RegistrationHandler.class);
        modEventBus.register(BlockEventHandler.class);
    }

    public static final ItemGroup EVOLUTION_GROUP = new ItemGroup("Evolution")
    {
        @Override
        public ItemStack createIcon()
        {
            return Index.Item.DEBUGGER.getDefaultInstance();
        }
    };

    public static Logger getLogger()
    {
        return LogManager.getLogger("Evolution Mod");
    }

}
