package com.estebes.compactic2generators.gui;

import com.estebes.compactic2generators.inventory.ContainerSimpleGenerator;
import com.estebes.compactic2generators.reference.Reference;
import com.estebes.compactic2generators.tileentity.TileEntitySimpleGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiSimpleGenerator extends GuiContainer
{
    public static final ResourceLocation guiSimpleGenerator = new ResourceLocation(Reference.LOWERCASE_MOD_ID, "textures/gui/GUIFluidGeneratorFinal.png");

    private TileEntitySimpleGenerator tileEntitySimpleGenerator;

    public  GuiSimpleGenerator(InventoryPlayer inventoryPlayer, TileEntitySimpleGenerator tileEntitySimpleGenerator)
    {
        super(new ContainerSimpleGenerator(inventoryPlayer, tileEntitySimpleGenerator));
        this.tileEntitySimpleGenerator = tileEntitySimpleGenerator;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        fontRendererObj.drawString("Stuff", 176 / 2 - fontRendererObj.getStringWidth("Stuff") / 2, 6, 0x404040);
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
