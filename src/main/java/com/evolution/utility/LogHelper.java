package com.evolution.utility;

import com.evolution.reference.ModInfo;
import net.minecraft.client.resources.I18n;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;

public class LogHelper
{
    public static Logger logger;

    public static void log(Level level, Message message){ logger.log(level, message); }

    public static void log(Level level, String message){ log(level, new SimpleMessage(message)); }

    public static void logLocalized(Level level, Message message){ log(level, new SimpleMessage("[" + ModInfo.MOD_NAME + "]: " + I18n.format(message.getFormattedMessage()))); }

    public static void logLocalized(Level level, Message message, Object... args){ logLocalized(level, new SimpleMessage(I18n.format(message.getFormattedMessage(), args))); }

    public static void logLocalized(Level level, String message){ logLocalized(level, new SimpleMessage("[" + ModInfo.MOD_NAME + "]: " + I18n.format(message))); }

    public static void logLocalized(Level level, String message, Object... args){ logLocalized(level, new SimpleMessage(I18n.format(message, args))); }

}
