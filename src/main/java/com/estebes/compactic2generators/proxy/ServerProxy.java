package com.estebes.compactic2generators.proxy;

import com.estebes.compactic2generators.renderer.blocks.RenderCobbleGenerator;
import com.estebes.compactic2generators.renderer.blocks.RenderSimpleGenerator;
import com.estebes.compactic2generators.tileentity.TileEntityCobbleGenerator;
import com.estebes.compactic2generators.tileentity.TileEntitySimpleGenerator;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ServerProxy
{
    public void registerRenderInformation()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySimpleGenerator.class, new RenderSimpleGenerator());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCobbleGenerator.class, new RenderCobbleGenerator());
    }
}
