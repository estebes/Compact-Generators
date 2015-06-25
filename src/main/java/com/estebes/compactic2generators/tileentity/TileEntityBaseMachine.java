package com.estebes.compactic2generators.tileentity;

import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityBaseMachine extends TileEntity implements IWrenchable
{
    // Misc Variables
    private ForgeDirection orientation;

    // Energy Variables
    private static final int MAX_STORED_ENERGY = 10000;
    private static final int MAX_VOLTAGE = 32;
    private boolean ENET_CHECKER;
    private int MAX_OPERATION_COST;
    private int STORED_ENERGY;
    private int CURRENT_OPERATION_COST;
    private int ENERGY_COST_PER_TICK;

    // Slot Variables

    private ItemStack[] machineInventory;


    public TileEntityBaseMachine()
    {
        this.orientation = ForgeDirection.SOUTH;
    }

    // Tile Entity orientation stuff
    public ForgeDirection getOrientation()
    {
        return this.orientation;
    }

    public void setOrientation(Object orientation)
    {
        if(orientation instanceof Integer)
        {
            this.orientation = ForgeDirection.getOrientation((Integer)orientation);
        }
        if(orientation instanceof ForgeDirection)
        {
            this.orientation = (ForgeDirection)orientation;
        }
    }


    // IWrenchable Stuff
    @Override
    public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side)
    {
        return ((side > 1 && side != this.orientation.ordinal()) ? true : false);
    }

    @Override
    public short getFacing()
    {
        return (short)this.orientation.ordinal();
    }

    @Override
    public void setFacing(short facing)
    {
        this.setOrientation((int) facing);
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public boolean wrenchCanRemove(EntityPlayer entityPlayer)
    {
        return (this.orientation.ordinal() > 1 ? true : false);
    }

    @Override
    public float getWrenchDropRate()
    {
        return 0.9F;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer)
    {
        return new ItemStack(getBlockType());
    }

}
