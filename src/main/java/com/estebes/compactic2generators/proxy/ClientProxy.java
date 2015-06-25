package com.estebes.compactic2generators.proxy;

import com.estebes.compactic2generators.init.BlockInit;
import com.estebes.compactic2generators.renderer.blocks.RenderBaseBlock;
import com.estebes.compactic2generators.renderer.blocks.RenderCobbleGenerator;
import com.estebes.compactic2generators.renderer.blocks.RenderPCBAssembler;
import com.estebes.compactic2generators.renderer.items.ItemRendererBaseBlock;
import com.estebes.compactic2generators.renderer.items.ItemRendererCobbleGenerator;
import com.estebes.compactic2generators.renderer.items.ItemRendererPCBAssembler;
import com.estebes.compactic2generators.renderer.items.ItemRendererSimpleGenerator;
import com.estebes.compactic2generators.renderer.blocks.RenderSimpleGenerator;
import com.estebes.compactic2generators.tileentity.TileEntityBaseBlock;
import com.estebes.compactic2generators.tileentity.TileEntityCobbleGenerator;
import com.estebes.compactic2generators.tileentity.TileEntityPCBAssembler;
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
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCobbleGenerator.class, new RenderCobbleGenerator());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockInit.cobbleGenerator), new ItemRendererCobbleGenerator());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPCBAssembler.class, new RenderPCBAssembler());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockInit.pcbAssembler), new ItemRendererPCBAssembler());
    }
}
