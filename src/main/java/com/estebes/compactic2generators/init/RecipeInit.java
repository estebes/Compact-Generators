package com.estebes.compactic2generators.init;

import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class RecipeInit
{
    public static void init()
    {
        Recipes.advRecipes.addRecipe(new ItemStack(BlockInit.simpleGenerator, 1, 0), "bg ", "gtg", " gb", 'g', IC2Items.getItem("semifluidGenerator"), 't', IC2Items.getItem("lvTransformer"),
                'b', IC2Items.getItem("reBattery"));

        Recipes.advRecipes.addRecipe(new ItemStack(BlockInit.cobbleGenerator, 1, 0), "cbc", "lmw", "cec", 'c', Blocks.cobblestone, 'b', IC2Items.getItem("ironblockcuttingblade"),
                'l', IC2Items.getItem("lavaCell"), 'm', IC2Items.getItem("machine"), 'w', IC2Items.getItem("waterCell"), 'e', IC2Items.getItem("electronicCircuit"));

        Recipes.advRecipes.addRecipe(new ItemStack(BlockInit.pcbAssembler, 1, 0), "wlw", "cac", "cfc", 'w', IC2Items.getItem("electricWrench"), 'l', IC2Items.getItem("miningLaser"),
                'c', IC2Items.getItem("advancedCircuit"), 'a', IC2Items.getItem("advancedMachine"), 'f', IC2Items.getItem("inductionFurnace"));

        Recipes.advRecipes.addRecipe(new ItemStack(BlockInit.mark3MachineCasing, 1, 0), "oso", "scs", "oso", 'o', IC2Items.getItem("denseplateobsidian"),
                's', IC2Items.getItem("plateadviron"), 'c', IC2Items.getItem("advancedMachine"));

        Recipes.advRecipes.addRecipe(new ItemStack(BlockInit.mark3MachineCasing, 1, 0), "sos", "oco", "sos", 's', IC2Items.getItem("plateadviron"),
                'o', IC2Items.getItem("denseplateobsidian"), 'c', IC2Items.getItem("advancedMachine"));

        Recipes.advRecipes.addRecipe(new ItemStack(BlockInit.mark4MachineCasing, 1, 0), "sis", "ici", "sis", 's', IC2Items.getItem("denseplateadviron"),
                'i', IC2Items.getItem("iridiumPlate"), 'c', BlockInit.mark3MachineCasing);

        Recipes.advRecipes.addRecipe(new ItemStack(BlockInit.mark4MachineCasing, 1, 0), "isi", "scs", "isi", 'i', IC2Items.getItem("iridiumPlate"),
                's', IC2Items.getItem("denseplateadviron"), 'c', BlockInit.mark3MachineCasing);

        Recipes.advRecipes.addRecipe(new ItemStack(BlockInit.treeHarvester, 1, 0), " t ", "ece", "   ", 'c', IC2Items.getItem("machine"),
                'e', IC2Items.getItem("electronicCircuit"), 't', IC2Items.getItem("electricTreetap"));

        Recipes.advRecipes.addShapelessRecipe(new ItemStack(BlockInit.electricItemBuffer, 1, 0), IC2Items.getItem("glassFiberCableItem"),
                IC2Items.getItem("itembuffer"));
    }
}
