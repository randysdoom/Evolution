package com.evolution.gui;

import com.evolution.utility.ResourceLocationHelper;
import javafx.util.Pair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import static com.evolution.reference.ModInfo.MOD_ID;

public class GuiBase extends GuiScreen
{
    private final Pair<Integer, Integer> backgroundDimensions;
    private final boolean doesPauseGame;

    int sizeWidth;
    int sizeHeight;
    int guiTop;
    int guiLeft;

    public GuiBase()
    {
        this(true);
    }

    public GuiBase(boolean doesPauseGame)
    {
        this.backgroundDimensions = ResourceLocationHelper.getImageDimensions(getBackground());
        this.doesPauseGame = doesPauseGame;
        this.sizeHeight = backgroundDimensions.getKey();
        this.sizeWidth = backgroundDimensions.getValue();
        this.guiLeft = (this.width - sizeWidth) / 2;
        this.guiTop = (this.height - sizeHeight) / 2;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.zLevel = 0F;
        drawBackground(0);
        this.zLevel = 15F;
        drawForeground();
    }

    @Override
    public void drawBackground(int tint)
    {
        mc.renderEngine.bindTexture(getBackground());
        drawTexturedModalRect((float) guiLeft, (float) guiTop, 0, 0, backgroundDimensions.getValue(), backgroundDimensions.getKey());
    }

    public void drawForeground()
    {

    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return doesPauseGame;
    }

    public ResourceLocation getBackground()
    {
        return new ResourceLocation(MOD_ID, "textures/gui/background/blank.png");
    }

}
