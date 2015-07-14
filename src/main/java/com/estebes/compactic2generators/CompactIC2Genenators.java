package com.estebes.compactic2generators;

import com.estebes.compactic2generators.gui.GuiHandler;
import com.estebes.compactic2generators.init.BlockInit;
import com.estebes.compactic2generators.init.ItemInit;
import com.estebes.compactic2generators.init.RecipeInit;
import com.estebes.compactic2generators.init.TileEntityInit;
import com.estebes.compactic2generators.network.PacketHandler;
import com.estebes.compactic2generators.proxy.ServerProxy;
import com.estebes.compactic2generators.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.item.Item;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class CompactIC2Genenators
{
    @SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_SERVER)
    public static ServerProxy proxy;

    @Instance(Reference.MOD_ID)
    public static CompactIC2Genenators instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent preinit)
    {
        PacketHandler.init();

        BlockInit.init();

        ItemInit.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent init)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        TileEntityInit.init();

        proxy.registerRenderInformation();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent postinit)
    {
        RecipeInit.init();
    }

}
