package com.estebes.compactic2generators.init;

import com.estebes.compactic2generators.tileentity.TileEntitySimpleGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityInit
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntitySimpleGenerator.class, "TileEntitySimpleGenerator");
    }
}
