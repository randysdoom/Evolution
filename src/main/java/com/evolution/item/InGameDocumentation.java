package com.evolution.item;

import com.evolution.item.base.ModItem;
import com.evolution.reference.ModInfo;
import com.google.gson.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class InGameDocumentation extends ModItem
{

    public InGameDocumentation()
    {
        super("documentation");
        this.setMaxStackSize(1);
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        if(itemStack.getTagCompound() != null)
        {
            NBTTagCompound nbt = itemStack.getTagCompound();
            if(nbt.hasKey("title") && !nbt.getString("title").equals(""))
            {
                return nbt.getString("title");
            }
        }
        return super.getItemStackDisplayName(itemStack);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemStack = playerIn.getHeldItem(handIn);

        if(!worldIn.isRemote)
        {
            this.resolveContents(itemStack, playerIn);
        }

        if(!playerIn.isSneaking())
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBook(playerIn, itemStack, false));
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
        }
        else
        {
            playerIn.replaceItemInInventory(playerIn.inventory.getSlotFor(itemStack), this.resolveContents(itemStack, playerIn));
            return new ActionResult<>(EnumActionResult.FAIL, itemStack);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if(flagIn.isAdvanced() && stack.getTagCompound() != null)
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            tooltip.add(gson.toJson(stack.getTagCompound()));
        }
    }

    private ItemStack resolveContents(ItemStack itemStack, EntityPlayer player)
    {
        if(player == null){ player = (EntityPlayer) Minecraft.getMinecraft().getRenderViewEntity(); }

        if (itemStack.getTagCompound() == null)
        {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setString("title", I18n.format("item.documentation.name"));
            nbt.setString("author", "Evolution Mod");
            itemStack.setTagCompound(nbt);
        }

        NBTTagCompound nbt = itemStack.getTagCompound();

        if(!nbt.getBoolean("generated"))
        {
            try
            {
                final String MC_VERSION = Minecraft.getMinecraft().getVersion();
                final String MOD_VERSION = ModInfo.MOD_VERSION;
                final String DOC_LOC = String.format("https://raw.githubusercontent.com/randysdoom/Evolution/master/documentation/%s/%s/", MC_VERSION, MOD_VERSION);
                URL doc_mapping = new URL(DOC_LOC + "index.json");
                HttpURLConnection request = (HttpURLConnection) doc_mapping.openConnection();
                request.connect();
                JsonParser parser = new JsonParser();
                JsonObject mappings = parser.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();
                request.disconnect();

                List<URL> list = new ArrayList<>();

                for(Map.Entry<String, JsonElement> mapping : mappings.entrySet())
                {
                    if(!mapping.getKey().equals("") && !mapping.getValue().isJsonNull())
                    {
                        URL pageURL = new URL(DOC_LOC + mapping.getValue().getAsJsonObject().get("location").getAsString() + ".txt");
                        list.add(pageURL);
                    }
                }

                NBTTagList pages = nbt.getTagList("pages", 8);

                if(list.size() > 0)
                {
                    for(URL url : list)
                    {
                        StringBuilder pageContents = new StringBuilder();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

                        String inputLine;
                        while((inputLine = reader.readLine()) != null)
                        {
                            String text = I18n.format(inputLine);
                            if(reader.readLine() == null || reader.readLine().equals("")){ text += " \n"; }
                            pageContents.append(text);
                        }

                        pages.appendTag(new NBTTagString(pageContents.toString()));
                    }

                    nbt.setTag("pages", pages);
                }
                else
                {
                    pages.appendTag(new NBTTagString(I18n.format("documentation.invalid")));
                }

                if (player instanceof EntityPlayerMP && player.getHeldItemMainhand() == itemStack)
                {
                    Slot slot = player.openContainer.getSlotFromInventory(player.inventory, player.inventory.currentItem);
                    ((EntityPlayerMP)player).connection.sendPacket(new SPacketSetSlot(0, slot.slotNumber, itemStack));
                }

            }
            catch(IOException ex){ ex.printStackTrace(); }
            finally{ nbt.setBoolean("generated", true); }
        }
        return itemStack;
    }

}
