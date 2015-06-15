package com.estebes.compactic2generators.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySimpleGenerator extends TileEntity
{
    private ForgeDirection orientation;
    private Boolean isWorking;

    public TileEntitySimpleGenerator()
    {
        orientation = ForgeDirection.SOUTH;
        this.isWorking = false;
    }

    public ForgeDirection getOrientation()
    {
        return this.orientation;
    }

    public void setOrientation(ForgeDirection orientation)
    {
        this.orientation = orientation;
    }

    public void setOrientation(int orientation)
    {
        this.orientation = ForgeDirection.getOrientation(orientation);
    }

    public boolean getWorkingState()
    {
        return this.isWorking;
    }

    public void setWorkingState()
    {
        this.isWorking = (this.isWorking == false) ? true : false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        if (nbtTagCompound.hasKey("teDirection"))
        {
            this.orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte("teDirection"));
        }
        if (nbtTagCompound.hasKey("teWorking"))
        {
            this.isWorking = nbtTagCompound.getBoolean("teWorking");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setByte("teDirection", (byte) this.orientation.ordinal());
        nbtTagCompound.setBoolean("teWorking", this.isWorking);
    }
}
