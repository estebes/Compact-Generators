package com.estebes.compactic2generators.init;

import com.estebes.compactic2generators.tileentity.TileEntityBaseBlock;
import com.estebes.compactic2generators.tileentity.TileEntityCobbleGenerator;
import com.estebes.compactic2generators.tileentity.TileEntityPCBAssembler;
import com.estebes.compactic2generators.tileentity.TileEntitySimpleGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityInit
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntitySimpleGenerator.class, "TileEntitySimpleGenerator");
        GameRegistry.registerTileEntity(TileEntityCobbleGenerator.class, "TileEntityCobbleGenerator");
        GameRegistry.registerTileEntity(TileEntityPCBAssembler.class, "TileEntityPCBAssembler");
        GameRegistry.registerTileEntity(TileEntityBaseBlock.class, "TileEntityBaseBlock");
    }
}
