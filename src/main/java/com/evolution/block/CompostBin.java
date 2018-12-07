package com.evolution.block;

import com.evolution.block.base.ModBlock;
import com.evolution.item.base.ModItemBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.evolution.reference.ModInfo.MOD_ID;

public class CompostBin extends ModBlock implements ITileEntityProvider
{

    public CompostBin()
    {
        super(Material.WOOD, "compost_bin");
    }

    @Override
    public boolean isOpaqueCube(IBlockState state){ return false; }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
            if(!playerIn.isSneaking())
            {
                //TODO: FILL BIN BASED ON ITEM COMPOST VALUE, DETERMINE COLOUR OF COMPOST VIA COLOR INDEX OF ITEM
            }
        }
        return true;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(this, 1, 0));

        if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileCompostBin)
        {
            TileCompostBin bin = (TileCompostBin) world.getTileEntity(pos);
            for(ItemStack stack : bin.contents){ if(stack != null){ drops.add(stack); } }
        }

        return drops;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileCompostBin();
    }

    public static class CompostBinItem extends ModItemBlock
    {

        public CompostBinItem(CompostBin block)
        {
            super(block);
        }

        @Override
        public void registerItemModels()
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(MOD_ID, "compost_bin"), "inventory"));
        }

    }

    public static class TileCompostBin extends TileEntity implements ITickable
    {
        public List<ItemStack> contents = new ArrayList<>();

        public TileCompostBin(){}

        @Override
        public void update()
        {
            if(contents.size() == 16)
            {

            }
        }

    }

//    public static class Renderer extends TileEntitySpecialRenderer<TileCompostBin>
//    {
//        private int renderTick = 0;
//        private boolean isRecolourComplete = false;
//
//        @Override
//        public void renderTileEntityAt(TileCompostBin tileEntity, double x, double y, double z, float partialTicks, int destroyStage)
//        {
//            GlStateManager.pushMatrix();
//            GlStateManager.translate(x, y, z);
//
//            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("blocks/planks_oak"));
//
//            if(tileEntity.contents.size() > 0)
//            {
//                Tessellator tessellator = Tessellator.getInstance();
//                VertexBuffer vertexBuffer = tessellator.getBuffer();
//                vertexBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
//                vertexBuffer.noColor();
//                vertexBuffer.pos(0, 0, 0).tex(0, 0).endVertex();
//                vertexBuffer.pos(0, 1, 0).tex(0, 1).endVertex();
//                vertexBuffer.pos(1, 1, 0).tex(1, 1).endVertex();
//                vertexBuffer.pos(1, 0, 0).tex(1, 0).endVertex();
//                tessellator.draw();
//            }
//
//            GlStateManager.popMatrix();
//        }
//
//    }

}
