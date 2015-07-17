package com.estebes.compactic2generators.tileentity;

import com.estebes.compactic2generators.network.PacketHandler;
import com.estebes.compactic2generators.network.message.MessageTileEntityEnergyMachine;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergyMachine extends TileEntityInventoryMachine implements IEnergySink
{
    // Energy Variables
    private int storedEnergy;
    private final int maxStoredEnergy;
    private final int sinkTier;
    private final int maxVoltage;
    private final int energyCostTick;
    private boolean enetChecker;


    // Constructor
    public TileEntityEnergyMachine(int maxStoredEnergy, int sinkTier, int energyCostTick, int inventorySize)
    {
        super(inventorySize);
        this.maxStoredEnergy = maxStoredEnergy;
        this.sinkTier = sinkTier;
        this.energyCostTick = energyCostTick;
        this.maxVoltage = (int)(32 * Math.pow(4, sinkTier - 1));
    }


    // NBT Stuff
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey("tileEntityStoredEnergy"))
        {
            storedEnergy = nbtTagCompound.getInteger("tileEntityStoredEnergy");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("tileEntityStoredEnergy", storedEnergy);
    }


    // ENET Stuff
    @Override
    public void invalidate()
    {
        if(!worldObj.isRemote && enetChecker)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            enetChecker = false;
        }
        super.invalidate();
    }

    @Override
    public void onChunkUnload()
    {
        if(!worldObj.isRemote && enetChecker)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            enetChecker = false;
        }
        super.onChunkUnload();
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if(worldObj.isRemote)
        {
            return;
        }

        if(!enetChecker)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            enetChecker = true;
        }

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        markDirty();
    }


    // IEnergySink Stuff
    @Override
    public double getDemandedEnergy()
    {
        return storedEnergy < maxStoredEnergy ? maxVoltage : 0;
    }

    @Override
    public int getSinkTier()
    {
        return sinkTier;
    }

    @Override
    public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage)
    {
        if(storedEnergy >= maxStoredEnergy)
        {
            return amount;
        }

        storedEnergy += Math.min(maxVoltage, (int)amount);
        return amount - Math.min(maxVoltage, (int)amount);
    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction)
    {
        return true;
    }


    // Var Stuff
    public int getStoredEnergy()
    {
        return storedEnergy;
    }

    public void setStoredEnergy(int value)
    {
        storedEnergy = value;
    }

    public int getMaxStoredEnergy()
    {
        return maxStoredEnergy;
    }

    public int getMaxVoltage()
    {
        return maxVoltage;
    }

    public int getEnergyCostTick()
    {
        return energyCostTick;
    }


    // Energy Level
    public int getScaledEnergy()
    {
        return storedEnergy * 13 / maxStoredEnergy;
    }


    // Network Stuff
    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityEnergyMachine(this));
    }
}
