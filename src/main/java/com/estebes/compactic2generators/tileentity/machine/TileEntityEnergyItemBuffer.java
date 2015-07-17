package com.estebes.compactic2generators.tileentity.machine;

import com.estebes.compactic2generators.network.PacketHandler;
import com.estebes.compactic2generators.network.message.MessageTileEntityEnergyItemBuffer;
import com.estebes.compactic2generators.tileentity.TileEntityEnergyMachine;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergyItemBuffer extends TileEntityEnergyMachine
{
    // Variables
    private boolean enetChecker;


    // Constructor
    public TileEntityEnergyItemBuffer(int maxStoredEnergy, int sinkTier, int energyCostTick, int inventorySize)
    {
        super(maxStoredEnergy, sinkTier, energyCostTick, inventorySize);
    }

}
