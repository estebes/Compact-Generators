package com.estebes.compactic2generators.renderer.items;

import com.estebes.compactic2generators.model.ModelSimpleGenerator;
import com.estebes.compactic2generators.reference.Reference;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ItemRendererCobbleGenerator implements IItemRenderer
{
    private final ModelSimpleGenerator modelSimpleGenerator;

    private static final ResourceLocation simpleGeneratorTexture = new ResourceLocation(Reference.LOWERCASE_MOD_ID + ":" + "textures/models/SimpleGenerator.png");

    public ItemRendererCobbleGenerator()
    {
        modelSimpleGenerator = new ModelSimpleGenerator();
    }

    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType itemRenderType)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType itemRenderType, ItemStack itemStack, ItemRendererHelper itemRendererHelper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... data)
    {
        switch (itemRenderType)
        {
            case ENTITY:
            {
                renderSimpleGenerator(0.5F, 0.5F, 0.5F);
                return;
            }
            case EQUIPPED:
            {
                renderSimpleGenerator(0.0F, 0.0F, 0.0F);
                return;
            }
            case EQUIPPED_FIRST_PERSON:
            {
                renderSimpleGenerator(0.0F, 0.0F, 0.0F);
                return;
            }
            case INVENTORY:
            {
                renderSimpleGenerator(0.0F, -0.078F, 0.0F);
                return;
            }
            default:
            {
            }
        }
    }

    private void renderSimpleGenerator(float x, float y, float z)
    {
        GL11.glPushMatrix();

        GL11.glTranslatef((float) x + 0.5F, (float) y - 0.5F, (float) z + 0.5F);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(simpleGeneratorTexture);
        modelSimpleGenerator.renderModel(0.0625F);

        GL11.glPopMatrix();
    }
}
