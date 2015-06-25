package com.estebes.compactic2generators.tileentity;

import com.estebes.compactic2generators.network.PacketHandler;
import com.estebes.compactic2generators.network.message.MessageTileEntitySimpleGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySimpleGenerator extends TileEntityBaseMachine
{
    private ForgeDirection orientation;
    private Boolean isWorking;

    public TileEntitySimpleGenerator()
    {
        this.isWorking = false;
        this.orientation = getOrientation();
    }

    public boolean getWorkingState()
    {
        return this.isWorking;
    }

    public void setWorkingState(Boolean isWorking)
    {
        this.isWorking = isWorking;
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

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntitySimpleGenerator(this));
    }
}
