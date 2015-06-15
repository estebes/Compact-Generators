package com.estebes.compactic2generators.proxy;

import com.estebes.compactic2generators.renderer.RenderSimpleGenerator;
import com.estebes.compactic2generators.tileentity.TileEntitySimpleGenerator;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ServerProxy
{
    public void registerRenderInformation()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySimpleGenerator.class, new RenderSimpleGenerator());
    }
}
