package com.estebes.compactic2generators.tileentity;

import com.estebes.compactic2generators.network.PacketHandler;
import com.estebes.compactic2generators.network.message.MessageTileEntityCobbleGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCobbleGenerator extends TileEntity implements IInventory, IWrenchable, IEnergySink
{
    // Energy parameters
    private static final int MAX_STORED_ENERGY = 10000;
    private static final int MAX_VOLTAGE = 32;
    private int storedEnergy = 0;

    private ForgeDirection orientation;
    private Boolean isWorking;
    private Boolean enetPresent = false;

    public TileEntityCobbleGenerator()
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
        if (nbtTagCompound.hasKey("teEnergyStored"))
        {
            this.storedEnergy = nbtTagCompound.getInteger("teEnergyStored");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setByte("teDirection", (byte) this.orientation.ordinal());
        nbtTagCompound.setBoolean("teWorking", this.isWorking);
        nbtTagCompound.setInteger("teEnergyStored", this.storedEnergy);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityCobbleGenerator(this));
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
    public void openInventory()
    {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

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
    }

    @Override
    public boolean wrenchCanRemove(EntityPlayer entityPlayer)
    {
        return (this.orientation.ordinal() > 1 ? true : false);
    }

    @Override
    public float getWrenchDropRate()
    {
        return 1.0F;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer)
    {
        return null;
    }

    @Override
    public double getDemandedEnergy()
    {
        return storedEnergy < MAX_STORED_ENERGY ? MAX_VOLTAGE : 0;
    }

    @Override
    public int getSinkTier()
    {
        return 1;
    }

    @Override
    public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage)
    {
        if(storedEnergy >= MAX_STORED_ENERGY)
        {
            return amount;
        }

        storedEnergy += Math.min(MAX_VOLTAGE, (int)amount);
        return amount - Math.min(MAX_VOLTAGE, (int)amount);
    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction)
    {
        return true;
    }

    public int getEnergyStored()
    {
        return this.storedEnergy;
    }

    public void setStoredEnergy(int storedEnergy)
    {
        this.storedEnergy = storedEnergy;
    }

    @Override
    public void invalidate()
    {
        if(!worldObj.isRemote && this.enetPresent)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            this.enetPresent = false;
        }
        super.invalidate();
    }

    @Override
    public void onChunkUnload()
    {
        if(!worldObj.isRemote && this.enetPresent)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            this.enetPresent = false;
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

        if(!this.enetPresent)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            this.enetPresent = true;
        }

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
}
