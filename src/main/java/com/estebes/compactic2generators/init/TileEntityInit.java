package com.estebes.compactic2generators.init;

import com.estebes.compactic2generators.tileentity.*;
import com.estebes.compactic2generators.tileentity.machine.TileEntityPCBAssembler;
import com.estebes.compactic2generators.tileentity.machine.TileEntityTreeHarvester;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityInit
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntitySimpleGenerator.class, "TileEntitySimpleGenerator");
        GameRegistry.registerTileEntity(TileEntityCobbleGenerator.class, "TileEntityCobbleGenerator");
        GameRegistry.registerTileEntity(TileEntityPCBAssembler.class, "TileEntityPCBAssembler");
        for(int i = 1; i < 5; i++)
        {
            GameRegistry.registerTileEntity(TileEntityCobbleMachine.class, "TileEntityCobbleMachineMark" + i);
        }
        GameRegistry.registerTileEntity(TileEntityTreeHarvester.class, "TileEntityTreeHarvester");
    }
}
