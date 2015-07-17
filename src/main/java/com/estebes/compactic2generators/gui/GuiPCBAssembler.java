package com.estebes.compactic2generators.gui;

import com.estebes.compactic2generators.inventory.ContainerPCBAssembler;
import com.estebes.compactic2generators.reference.Reference;
import com.estebes.compactic2generators.tileentity.machine.TileEntityPCBAssembler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiPCBAssembler extends GuiContainer
{
    public static final ResourceLocation guiSimpleGenerator = new ResourceLocation(Reference.LOWERCASE_MOD_ID, "textures/gui/GUIPCBAssembler.png");

    private TileEntityPCBAssembler tileEntityPCBAssembler;

    public  GuiPCBAssembler(InventoryPlayer inventoryPlayer, TileEntityPCBAssembler tileEntityPCBAssembler)
    {
        super(new ContainerPCBAssembler(inventoryPlayer, tileEntityPCBAssembler));
        this.tileEntityPCBAssembler = tileEntityPCBAssembler;
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

        int scaledProgress = this.tileEntityPCBAssembler.getScaledProgress();
        int scaledEnergy = this.tileEntityPCBAssembler.getScaledEnergy();
        //drawTexturedModalRect(guiLeft + 59, guiTop + 50 - scaledEnergy, 179, 14 - scaledEnergy, 7, scaledEnergy);
        drawTexturedModalRect(guiLeft + 79, guiTop + 24, 177, 0, scaledProgress, 18);
    }
}
