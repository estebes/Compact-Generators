package com.estebes.compactic2generators.gui;

import com.estebes.compactic2generators.inventory.ContainerCobbleGenerator;
import com.estebes.compactic2generators.reference.Reference;
import com.estebes.compactic2generators.tileentity.TileEntityCobbleGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiCobbleGenerator extends GuiContainer
{
    public static final ResourceLocation guiSimpleGenerator = new ResourceLocation(Reference.LOWERCASE_MOD_ID, "textures/gui/GUIFluidGeneratorFinal.png");

    private TileEntityCobbleGenerator tileEntityCobbleGenerator;

    public  GuiCobbleGenerator(InventoryPlayer inventoryPlayer, TileEntityCobbleGenerator tileEntityCobbleGenerator)
    {
        super(new ContainerCobbleGenerator(inventoryPlayer, tileEntityCobbleGenerator));
        this.tileEntityCobbleGenerator = tileEntityCobbleGenerator;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        fontRendererObj.drawString("Energy: " + tileEntityCobbleGenerator.getEnergyStored(), 176 / 2 - fontRendererObj.getStringWidth("Stuff") / 2, 6, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(guiSimpleGenerator);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}
