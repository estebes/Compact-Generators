package com.estebes.compactic2generators.renderer.blocks;

import com.estebes.compactic2generators.model.ModelSimpleGenerator;
import com.estebes.compactic2generators.reference.Reference;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


public class RenderBaseBlock extends TileEntitySpecialRenderer
{
    private static final ResourceLocation cobbleGeneratorTexture = new ResourceLocation(Reference.LOWERCASE_MOD_ID + ":" + "textures/models/Mark3MachineCasing.png");

    private ModelSimpleGenerator modelSimpleGenerator;

    public RenderBaseBlock()
    {
        this.modelSimpleGenerator = new ModelSimpleGenerator();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        if (tileEntity != null)
        {
            this.bindTexture(cobbleGeneratorTexture);

            GL11.glPushMatrix();

            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);

            this.modelSimpleGenerator.renderModel(0.0625F);

            GL11.glPopMatrix();
        }
    }
}
