package com.estebes.compactic2generators.init;

import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;
import net.minecraft.item.ItemStack;

public class RecipeInit
{
    public static void init()
    {
        GameRegistry.addShapedRecipe(new ItemStack(BlockInit.simpleGenerator, 1, 0), "bg ", "gtg", " gb", 'g', IC2Items.getItem("semifluidGenerator"), 't', IC2Items.getItem("lvTransformer"), 'b', IC2Items.getItem("reBattery"));
    }
}
