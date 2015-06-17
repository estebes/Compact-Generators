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

public class TileEntitySimpleGenerator extends TileEntity implements IInventory
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

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {

    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }
}
