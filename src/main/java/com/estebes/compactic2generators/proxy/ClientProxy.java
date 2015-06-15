package com.estebes.compactic2generators.proxy;

import com.estebes.compactic2generators.init.BlockInit;
import com.estebes.compactic2generators.renderer.ItemRendererSimpleGenerator;
import com.estebes.compactic2generators.renderer.RenderSimpleGenerator;
import com.estebes.compactic2generators.tileentity.TileEntitySimpleGenerator;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy
{
    public void registerRenderInformation()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySimpleGenerator.class, new RenderSimpleGenerator());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockInit.simpleGenerator), new ItemRendererSimpleGenerator());
    }
}
