package com.estebes.compactic2generators.init;

import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;
import net.minecraft.item.ItemStack;

public class RecipeInit
{
    public static void init()
    {
        GameRegistry.addShapedRecipe(new ItemStack(BlockInit.simpleGenerator, 1, 0), " g ", "gtg", " g ", 'g', IC2Items.getItem("semifluidGenerator"), 't', IC2Items.getItem("mvTransformer"));
    }
}
