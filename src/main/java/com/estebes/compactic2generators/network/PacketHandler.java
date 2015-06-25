package com.estebes.compactic2generators.network;

import com.estebes.compactic2generators.network.message.MessageTileEntityCobbleMachine;
import com.estebes.compactic2generators.network.message.MessageTileEntityPCBAssembler;
import com.estebes.compactic2generators.network.message.MessageTileEntitySimpleGenerator;
import com.estebes.compactic2generators.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.LOWERCASE_MOD_ID);

    public static void init()
    {
        INSTANCE.registerMessage(MessageTileEntitySimpleGenerator.class, MessageTileEntitySimpleGenerator.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityCobbleMachine.class, MessageTileEntityCobbleMachine.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityPCBAssembler.class, MessageTileEntityPCBAssembler.class, 2, Side.CLIENT);
    }
}
