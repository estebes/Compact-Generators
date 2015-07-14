package com.estebes.compactic2generators.gui;

import com.estebes.compactic2generators.inventory.ContainerCobbleGenerator;
import com.estebes.compactic2generators.reference.Reference;
import com.estebes.compactic2generators.tileentity.TileEntityCobbleGenerator;
import com.estebes.compactic2generators.tileentity.TileEntityCobbleMachine;
import ic2.api.item.IC2Items;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiCobbleGenerator extends GuiContainer
{
    public static final ResourceLocation guiSimpleGenerator = new ResourceLocation(Reference.LOWERCASE_MOD_ID, "textures/gui/GUICobbleGenerator.png");

    private TileEntityCobbleMachine tileEntityCobbleMachine;

    public  GuiCobbleGenerator(InventoryPlayer inventoryPlayer, TileEntityCobbleMachine tileEntityCobbleMachine)
    {
        super(new ContainerCobbleGenerator(inventoryPlayer, new TileEntityCobbleGenerator()));
        this.tileEntityCobbleMachine = tileEntityCobbleMachine;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        fontRendererObj.drawString(tileEntityCobbleMachine.getInventoryName(), 176 / 2 - fontRendererObj.getStringWidth("") / 2, 6, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(guiSimpleGenerator);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        /*int scaledProgress = this.tileEntityCobbleGenerator.getScaledProgress();
        int scaledEnergy = this.tileEntityCobbleGenerator.getScaledEnergy();
        drawTexturedModalRect(guiLeft + 59, guiTop + 50 - scaledEnergy, 179, 14 - scaledEnergy, 7, scaledEnergy);
        drawTexturedModalRect(guiLeft + 80, guiTop + 34, 177, 14, scaledProgress, 19);*/
        itemRender.renderItemIntoGUI(fontRendererObj, mc.getTextureManager(), new ItemStack(Blocks.flowing_water), guiLeft + 56, guiTop + 17);
        itemRender.renderItemIntoGUI(fontRendererObj, mc.getTextureManager(), new ItemStack(Blocks.flowing_lava), guiLeft + 56, guiTop + 53);
    }
}
