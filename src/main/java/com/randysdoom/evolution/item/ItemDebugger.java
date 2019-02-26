package com.randysdoom.evolution.item;

import com.randysdoom.evolution.item.base.ModItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemDebugger extends ModItem
{

    public ItemDebugger()
    {
        super(new Item.Properties(), "debug");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
//        if(!worldIn.isRemote)
//        {
//            IAttributes attr = playerIn.getCapability(Handler.ModCapabilities.ATTRIBUTES, null);
//            playerIn.sendMessage(new TextComponentString(""));
//            playerIn.sendMessage(new TextComponentString("Listing all Attribute Values for player: " + playerIn.getDisplayNameString()));
//            playerIn.sendMessage(getString("Charisma", attr.getCharismaLevel(), attr.getCharismaExperience()));
//            playerIn.sendMessage(getString("Constitution", attr.getConstitutionLevel(), attr.getConstitutionExperience()));
//            playerIn.sendMessage(getString("Dexterity", attr.getDexterityLevel(), attr.getDexterityExperience()));
//            playerIn.sendMessage(getString("Intelligence", attr.getIntelligenceLevel(), attr.getIntelligenceExperience()));
//            playerIn.sendMessage(getString("Luck", attr.getLuckLevel(), attr.getLuckExperience()));
//            playerIn.sendMessage(getString("Perception", attr.getPerceptionLevel(), attr.getPerceptionExperience()));
//            playerIn.sendMessage(getString("Strength", attr.getStrengthLevel(), attr.getStrengthExperience()));
//            playerIn.sendMessage(getString("Willpower", attr.getWillpowerLevel(), attr.getWillpowerExperience()));
//            playerIn.sendMessage(getString("Wisdom", attr.getWisdomLevel(), attr.getWisdomExperience()));
//        }
        if(!worldIn.isRemote){ playerIn.sendMessage(new TextComponentString("Right Click!")); }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private TextComponentString getString(String ATTR_NAME, int ATTR_LEVEL, int ATTR_EXP)
    {
        return new TextComponentString(String.format("%s Values: { Level: %s/100, Experience: %s/%s }", ATTR_NAME, ATTR_LEVEL, ATTR_EXP, ATTR_LEVEL > 1 ? (int)((25 * ATTR_LEVEL) * 1.25) : 25));
    }

}
