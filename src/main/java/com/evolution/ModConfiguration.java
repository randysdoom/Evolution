package com.evolution;

import com.evolution.reference.ModInfo;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

import static com.evolution.ModConfiguration.Core.*;
import static com.evolution.ModConfiguration.Features.*;

public class ModConfiguration
{

    private static Configuration CONF;
    private static final String CATEGORY_GENERAL = "General";

    public static void init(File configDirectory)
    {
        if(Core.Configuration == null)
        {
            Core.Configuration = new Configuration(new File(configDirectory, "Evolution/Core.cfg"), true);
            loadCoreConfiguration();
        }

        if(Features.Configuration == null)
        {
            Features.Configuration = new Configuration(new File(configDirectory, "Evolution/Features.cfg"), true);
            loadFeaturesConfiguration();
        }

        if(Progression.Configuration == null)
        {
            Progression.Configuration = new Configuration(new File(configDirectory, "Evolution/Progression.cfg"), true);
            loadProgressionConfiguration();
        }

        if(ModCompatability.Configuration == null)
        {
            ModCompatability.Configuration = new Configuration(new File(configDirectory, "Evolution/ModCompatability.cfg"), true);
            loadModCompatabilityConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigChangeEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        final String modID = event.getModID();
        final String configID = event.getConfigID();
        assert configID != null;
        if(modID.equalsIgnoreCase(ModInfo.MOD_ID))
        {
            if(configID.equalsIgnoreCase("Core"))
            {
                loadCoreConfiguration();
            }
            else if(configID.equalsIgnoreCase("Features"))
            {
                loadFeaturesConfiguration();
            }
            else if(configID.equalsIgnoreCase("Progression"))
            {
                loadProgressionConfiguration();
            }
            else if(configID.equalsIgnoreCase("ModCompatability"))
            {
                loadModCompatabilityConfiguration();
            }
        }
    }

    private static void loadCoreConfiguration()
    {
        CONF = Core.Configuration;

        modifyVanillaRecipes = CONF.getBoolean(I18n.format(MODIFY_VANILLA_RECIPES_NAME), CATEGORY_GENERAL + ".Modifications", Core.MODIFY_VANILLA_RECIPES_DEFAULT, I18n.format(MODIFY_VANILLA_RECIPES_COMMENT));

        isDebugLoggingEnabled = CONF.getBoolean(I18n.format(DEBUG_LOGGING_NAME), CATEGORY_GENERAL + ".Debug", Core.DEBUG_LOGGING_DEFAULT, I18n.format(DEBUG_LOGGING_COMMENT));

        isDebugManipulationEnabled = CONF.getBoolean(I18n.format(DEBUG_MANIPULATION_NAME), CATEGORY_GENERAL + ".Debug", DEBUG_MANIPULATION_DEFAULT, I18n.format(DEBUG_MANIPULATION_COMMENT));

        if(Core.Configuration.hasChanged())
        {
            Core.Configuration.save();
        }
    }

    private static void loadFeaturesConfiguration()
    {
        CONF = Features.Configuration;

        useCustomHealthBar = CONF.get(CATEGORY_HEALTH, CUSTOM_HEALTH_BAR_NAME, true, CUSTOM_HEALTH_BAR_COMMENT).getBoolean();

        customHealthBarStyle = CONF.get(CATEGORY_HEALTH, CUSTOM_HEALTH_BAR_STYLE_NAME, 0, CUSTOM_HEALTH_BAR_STYLE_COMMENT).getInt();

        if(Features.Configuration.hasChanged())
        {
            Features.Configuration.save();
        }
    }

    private static void loadProgressionConfiguration()
    {
        if(Progression.Configuration.hasChanged())
        {
            Progression.Configuration.save();
        }
    }

    private static void loadModCompatabilityConfiguration()
    {
        if(ModCompatability.Configuration.hasChanged())
        {
            ModCompatability.Configuration.save();
        }
    }

    public static class Core
    {
        static Configuration Configuration;

        public static boolean isDebugLoggingEnabled;
        static String DEBUG_LOGGING_NAME = "debug_logging.name";
        static String DEBUG_LOGGING_COMMENT = "debug_logging.comment";
        static final boolean DEBUG_LOGGING_DEFAULT = false;

        public static boolean isDebugManipulationEnabled;
        static String DEBUG_MANIPULATION_NAME = "debug_manipulation.name";
        static String DEBUG_MANIPULATION_COMMENT = "debug_manipulation.comment";
        static final boolean DEBUG_MANIPULATION_DEFAULT = false;
        public static boolean modifyVanillaRecipes;
        static String MODIFY_VANILLA_RECIPES_NAME = "modify_vanilla_recipes.name";
        static String MODIFY_VANILLA_RECIPES_COMMENT = "modify_vanilla_recipes.comment";
        static final boolean MODIFY_VANILLA_RECIPES_DEFAULT = true;

    }

    public static class Features
    {
        static Configuration Configuration;

        static final String CATEGORY_CUSTOM_HUD = CATEGORY_GENERAL + ".Custom In Game Hud";
        static final String CATEGORY_HEALTH = CATEGORY_CUSTOM_HUD + ".Health Bar";
        static final String CATEGORY_HUNGER = CATEGORY_CUSTOM_HUD + ".Hunger Bar";
        static final String CATEGORY_ARMOR = CATEGORY_CUSTOM_HUD + ".Armor Bar";
        static final String CATEGORY_HOTBAR = CATEGORY_CUSTOM_HUD + ".HotBar";
        static final String CATEGORY_OXYGEN = CATEGORY_CUSTOM_HUD + ".Oxygen Bar";
        static final String CATEGORY_MOUNT_HEALTH = CATEGORY_CUSTOM_HUD + ".Mount Health Bar";

        public static boolean useCustomHealthBar;
        static String CUSTOM_HEALTH_BAR_NAME = "custom_health_bar.name";
        static String CUSTOM_HEALTH_BAR_COMMENT = "custom_health_bar.comment";
        public static int customHealthBarStyle = 0;
        static String CUSTOM_HEALTH_BAR_STYLE_NAME = "custom_health_bar_style.name";
        static String CUSTOM_HEALTH_BAR_STYLE_COMMENT = "custom_health_bar_style.comment";
//        public static boolean useCustomHungerBar;
//        public static int customHungerBarStyle;
//        public static boolean useCustomExperienceBar;
//        public static int customExperienceBarStyle;
//        public static boolean useCustomArmorBar;
//        public static int customArmorBarStyle;
//        public static boolean useCustomHotBar;
//        public static int customHotBarStyle;
//        public static boolean useCustomOxygenBar;
//        public static int customOxygenBarStyle;
//        public static boolean useCustomMountHealthBar;
//        public static int customMountHealthBarStyle;

    }

    public static class Progression
    {
        static Configuration Configuration;
    }

    public static class ModCompatability
    {
        static Configuration Configuration;
    }

}
