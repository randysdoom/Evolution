package com.randysdoom.evolution.item;

import com.google.gson.*;
import com.randysdoom.evolution.item.base.ModItem;
import com.randysdoom.evolution.reference.ModInformation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InGameDocumentation extends ModItem
{

    public InGameDocumentation()
    {
        super(new Properties().maxStackSize(1), "documentation");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemStack = playerIn.getHeldItem(handIn);

        if (!worldIn.isRemote)
        {
            this.resolveContents(itemStack, playerIn);
        }

        if (!playerIn.isSneaking())
        {
//            Minecraft.getInstance().displayGuiScreen(new GuiScreenBook(playerIn, itemStack, false, handIn));
            return new ActionResult<>(ActionResultType.SUCCESS, itemStack);
        } else
        {
            playerIn.replaceItemInInventory(playerIn.inventory.getSlotFor(itemStack), this.resolveContents(itemStack, playerIn));
            return new ActionResult<>(ActionResultType.FAIL, itemStack);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        if (flagIn.isAdvanced() && stack.getTag() != null)
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            tooltip.add(new StringTextComponent(gson.toJson(stack.getTag())));
        }
    }

    private ItemStack resolveContents(ItemStack itemStack, PlayerEntity player)
    {
        if (player == null)
        {
            player = (PlayerEntity) Minecraft.getInstance().getRenderViewEntity();
        }

        if (itemStack.getTag() == null)
        {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("title", I18n.format("item.documentation.name"));
            nbt.putString("author", "Evolution Mod");
            itemStack.deserializeNBT(nbt);
        }

        CompoundNBT nbt = itemStack.getTag();

        if (!nbt.getBoolean("generated"))
        {
            try
            {
                final String MC_VERSION = Minecraft.getInstance().getVersion();
                final String MOD_VERSION = ModInformation.MOD_VERSION;
                final String DOC_LOC = String.format("https://raw.githubusercontent.com/randysdoom/Evolution/master/documentation/1.10.2/%s/", MOD_VERSION);
                URL doc_mapping = new URL(DOC_LOC + "index.json");
                HttpURLConnection request = (HttpURLConnection) doc_mapping.openConnection();
                request.connect();
                JsonParser parser = new JsonParser();
                JsonObject mappings = parser.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();
                request.disconnect();

                List<URL> list = new ArrayList<>();

                for (Map.Entry<String, JsonElement> mapping : mappings.entrySet())
                {
                    if (!mapping.getKey().equals("") && !mapping.getValue().isJsonNull())
                    {
                        URL pageURL = new URL(DOC_LOC + mapping.getValue().getAsJsonObject().get("location").getAsString() + ".txt");
                        list.add(pageURL);
                    }
                }

                ListNBT pages = nbt.getList("pages", 8);

                if (list.size() > 0)
                {
                    for (URL url : list)
                    {
                        StringBuilder pageContents = new StringBuilder();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

                        String inputLine;
                        while ((inputLine = reader.readLine()) != null)
                        {
                            String text = I18n.format(inputLine);
                            if (reader.readLine() == null || reader.readLine().equals(""))
                            {
                                text += " \n";
                            }
                            pageContents.append(text);
                        }

                        pages.add(new StringNBT(pageContents.toString()));
                    }

                    nbt.put("pages", pages);
                } else
                {
                    pages.add(new StringNBT(I18n.format("documentation.invalid")));
                }

                if (player instanceof ServerPlayerEntity && player.getHeldItemMainhand() == itemStack)
                {
                    Slot slot = player.openContainer.inventorySlots.get(player.inventory.currentItem);
                    ((ServerPlayerEntity) player).connection.sendPacket(new SSetSlotPacket(0, slot.slotNumber, itemStack));
                }

            } catch (IOException ex)
            {
                ex.printStackTrace();
            } finally
            {
                nbt.putBoolean("generated", true);
            }
        }
        return itemStack;
    }

}
