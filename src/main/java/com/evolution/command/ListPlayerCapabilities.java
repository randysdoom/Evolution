package com.evolution.command;

import com.evolution.capability.player.IAttributes;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class ListPlayerCapabilities extends CommandBase
{

    @Override
    public String getName()
    {
        return "List Player Capability";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "listPlayerCapabilities";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        EntityPlayer player = (EntityPlayer)sender.getCommandSenderEntity();
        assert player != null;

        if(player.getCapability(IAttributes.Provider.CAPABILITY, null) != null)
        {
            IAttributes attr = player.getCapability(IAttributes.Provider.CAPABILITY, null);
            player.sendMessage(new TextComponentString("Listing all Attribute Values for player: " + player.getDisplayNameString()));
            player.sendMessage(getString("Charisma", attr.getCharismaLevel(), attr.getCharismaExperience()));
            player.sendMessage(getString("Constitution", attr.getConstitutionLevel(), attr.getConstitutionExperience()));
            player.sendMessage(getString("Dexterity", attr.getDexterityLevel(), attr.getDexterityExperience()));
            player.sendMessage(getString("Intelligence", attr.getIntelligenceLevel(), attr.getIntelligenceExperience()));
            player.sendMessage(getString("Luck", attr.getLuckLevel(), attr.getLuckExperience()));
            player.sendMessage(getString("Perception", attr.getPerceptionLevel(), attr.getPerceptionExperience()));
            player.sendMessage(getString("Strength", attr.getStrengthLevel(), attr.getStrengthExperience()));
            player.sendMessage(getString("Willpower", attr.getWillpowerLevel(), attr.getWillpowerExperience()));
            player.sendMessage(getString("Wisdom", attr.getWisdomLevel(), attr.getWisdomExperience()));
        }
        else
        {
            player.sendMessage(new TextComponentString("Unable to execute command. Player Capability: Attribute is null. This should not happen."));
        }

    }

    private TextComponentString getString(String ATTR_NAME, int ATTR_LEVEL, int ATTR_EXP)
    {
        return new TextComponentString(String.format("%s Values: { Level: %s/100, Experience: %s/%s }", ATTR_NAME, ATTR_LEVEL, ATTR_EXP, ATTR_LEVEL > 1 ? (int)((25 * ATTR_LEVEL) * 1.25) : 25));
    }

}
