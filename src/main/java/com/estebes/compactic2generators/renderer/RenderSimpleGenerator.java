package com.estebes.compactic2generators.renderer;

import com.estebes.compactic2generators.model.ModelSimpleGenerator;
import com.estebes.compactic2generators.reference.Reference;
import com.estebes.compactic2generators.tileentity.TileEntitySimpleGenerator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class RenderSimpleGenerator extends TileEntitySpecialRenderer
{
    private static final ResourceLocation simpleGeneratorTexture = new ResourceLocation(Reference.LOWERCASE_MOD_ID + ":" + "textures/models/SimpleGenerator.png");
    private static final ResourceLocation simpleGeneratorWorkingTexture = new ResourceLocation(Reference.LOWERCASE_MOD_ID + ":" + "textures/models/SimpleGeneratorWorking.png");

    private ModelSimpleGenerator modelSimpleGenerator;

    public RenderSimpleGenerator()
    {
        this.modelSimpleGenerator = new ModelSimpleGenerator();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        if (tileEntity instanceof TileEntitySimpleGenerator)
        {
            TileEntitySimpleGenerator tileEntitySimpleGenerator = (TileEntitySimpleGenerator) tileEntity;
            ForgeDirection direction = null;

            if (tileEntitySimpleGenerator.getWorldObj() != null)
            {
                direction = tileEntitySimpleGenerator.getOrientation();
            }

            this.bindTexture((((TileEntitySimpleGenerator) tileEntity).getWorkingState() == false) ? simpleGeneratorTexture : simpleGeneratorWorkingTexture);

            GL11.glPushMatrix();

                GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
                GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
                GL11.glScalef(0.5F, 0.5F, 0.5F);

                GL11.glPushMatrix();

                    short angle = 0;
                    if (direction != null)
                    {
                        if (direction == ForgeDirection.NORTH)
                        {
                            angle = 0;
                        }
                        else if (direction == ForgeDirection.SOUTH)
                        {
                            angle = 180;
                        }
                        else if (direction == ForgeDirection.WEST)
                        {
                            angle = -90;
                        }
                        else if (direction == ForgeDirection.EAST)
                        {
                            angle = 90;
                        }
                    }
                    GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);

                    this.modelSimpleGenerator.renderModel(0.0625F);

                GL11.glPopMatrix();

            GL11.glPopMatrix();
        }
    }
}
