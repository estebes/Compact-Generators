package com.estebes.compactic2generators.gui;

import com.estebes.compactic2generators.inventory.ContainerElectricItemBuffer;
import com.estebes.compactic2generators.reference.Reference;
import com.estebes.compactic2generators.tileentity.machine.TileEntityElectricItemBuffer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


public class GuiElectricItemBuffer extends GuiContainer
{
    public static final ResourceLocation guiTreeHarvester = new ResourceLocation(Reference.LOWERCASE_MOD_ID, "textures/gui/GUIElectricItemBuffer.png");

    private TileEntityElectricItemBuffer tileEntityElectricItemBuffer;

    public GuiElectricItemBuffer(InventoryPlayer inventoryPlayer, TileEntityElectricItemBuffer tileEntityElectricItemBuffer)
    {
        super(new ContainerElectricItemBuffer(inventoryPlayer, tileEntityElectricItemBuffer));
        this.tileEntityElectricItemBuffer = tileEntityElectricItemBuffer;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        fontRendererObj.drawString("Electric Item Buffer", xSize / 2 - fontRendererObj.getStringWidth("Electric Item Buffer") / 2, 6, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(guiTreeHarvester);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}
