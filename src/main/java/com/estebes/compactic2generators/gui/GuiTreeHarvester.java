package com.estebes.compactic2generators.gui;

import com.estebes.compactic2generators.inventory.ContainerTreeHarvester;
import com.estebes.compactic2generators.reference.Reference;
import com.estebes.compactic2generators.tileentity.TileEntityTreeHarvester;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiTreeHarvester extends GuiContainer
{
    public static final ResourceLocation guiTreeHarvester = new ResourceLocation(Reference.LOWERCASE_MOD_ID, "textures/gui/GUIElectricTreeChopper.png");

    private TileEntityTreeHarvester tileEntityTreeHarvester;

    public  GuiTreeHarvester(InventoryPlayer inventoryPlayer, TileEntityTreeHarvester tileEntityTreeHarvester)
    {
        super(new ContainerTreeHarvester(inventoryPlayer, tileEntityTreeHarvester));
        this.tileEntityTreeHarvester = tileEntityTreeHarvester;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        fontRendererObj.drawString("Resin Harvester", xSize / 2 - fontRendererObj.getStringWidth("Resin Harvester") / 2, 6, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(guiTreeHarvester);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        int scaledEnergy = tileEntityTreeHarvester.getScaledEnergy();
        drawTexturedModalRect(guiLeft + 156, guiTop + 56 - scaledEnergy, 179, 14 - scaledEnergy, 7, scaledEnergy);
    }
}
