package com.estebes.compactic2generators.init;

import com.estebes.compactic2generators.item.IridiumCuttingBlade;
import com.estebes.compactic2generators.item.ItemRubberModule;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemInit
{
    public static final IridiumCuttingBlade iridiumCuttingBlade = new IridiumCuttingBlade();
    public static final ItemRubberModule itemRubberModule = new ItemRubberModule();

    public static void init()
    {
        GameRegistry.registerItem(iridiumCuttingBlade, "Iridium Cutting Blade");
        GameRegistry.registerItem(itemRubberModule, "Rubber Detector Module");
    }
}
