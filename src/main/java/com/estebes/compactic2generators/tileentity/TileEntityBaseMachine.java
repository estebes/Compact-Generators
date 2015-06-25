package com.estebes.compactic2generators.tileentity;

import com.estebes.compactic2generators.network.PacketHandler;
import com.estebes.compactic2generators.network.message.MessageTileEntityCobbleGenerator;
import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityBaseMachine extends TileEntity implements IWrenchable
{
    // Misc Variables
    private ForgeDirection orientation;

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


    // NBT Stuff
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey("tileEntityOrientation"))
        {
            this.orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte("tileEntityOrientation"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte("tileEntityOrientation", (byte) this.orientation.ordinal());
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


    // Network Stuff
    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityCobbleGenerator(null));
    }
}
