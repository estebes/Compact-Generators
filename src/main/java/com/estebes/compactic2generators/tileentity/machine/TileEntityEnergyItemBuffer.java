package com.estebes.compactic2generators.tileentity.machine;

import com.estebes.compactic2generators.tileentity.TileEntityEnergyMachine;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergyItemBuffer extends TileEntityEnergyMachine implements IEnergySource
{
    // Variables



    // Constructor
    public TileEntityEnergyItemBuffer(int maxStoredEnergy, int sinkTier, int energyCostTick, int inventorySize)
    {
        super(maxStoredEnergy, sinkTier, energyCostTick, inventorySize);
    }


    // Stuff
    @Override
    public void updateEntity()
    {
        super.updateEntity();

        markDirty();
    }


    // IEnergySource Stuff
    @Override
    public double getOfferedEnergy()
    {
        return Math.min(getStoredEnergy(), getMaxVoltage());
    }

    @Override
    public void drawEnergy(double amount)
    {
        setStoredEnergy((int)(getStoredEnergy() - amount));
    }

    @Override
    public int getSourceTier()
    {
        return 4;
    }

    @Override
    public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction)
    {
        return true;
    }
}
