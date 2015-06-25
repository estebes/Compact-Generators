package com.estebes.compactic2generators.init;

import com.estebes.compactic2generators.item.IridiumCuttingBlade;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemInit
{
    public static final IridiumCuttingBlade iridiumCuttingBlade = new IridiumCuttingBlade();

    public static void init()
    {
        GameRegistry.registerItem(iridiumCuttingBlade, "Iridium Cutting Blade");
    }
}
