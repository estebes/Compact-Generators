package com.estebes.compactic2generators.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSimpleGenerator extends ModelBase
{
    ModelRenderer simpleGenerator;

    public ModelSimpleGenerator()
    {
        textureWidth = 128;
        textureHeight = 64;

        simpleGenerator = new ModelRenderer(this, 0, 0);
        simpleGenerator.addBox(0F, 0F, 0F, 32, 32, 32);
        simpleGenerator.setRotationPoint(-16F, 16F, -16F);
        simpleGenerator.setTextureSize(128, 64);
        simpleGenerator.mirror = true;
        setRotation(simpleGenerator, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        simpleGenerator.render(f5);
    }

    public void renderModel(float f5)
    {
        simpleGenerator.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}