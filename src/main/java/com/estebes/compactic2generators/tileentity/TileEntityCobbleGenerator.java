package com.estebes.compactic2generators.tileentity;

import com.estebes.compactic2generators.init.BlockInit;
import com.estebes.compactic2generators.network.PacketHandler;
import com.estebes.compactic2generators.network.message.MessageTileEntityCobbleGenerator;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCobbleGenerator extends TileEntity implements ISidedInventory, IWrenchable, IEnergySink
{
    // Energy parameters
    private static final int MAX_STORED_ENERGY = 1000000;
    private static final int MAX_VOLTAGE = 2048;
    private int storedEnergy = 0;
    private int runningEUPerTick = 2048;
    private final int MAX_PROGRESS = 1000;
    public int energyUsed;

    private ForgeDirection orientation;
    private Boolean isWorking;
    private Boolean enetPresent = false;

    public int energyPixels;
    public int progressPixels;

    public byte stackSize, stackMeta;

    private ItemStack[] slots = new ItemStack[1];

    public TileEntityCobbleGenerator()
    {
        orientation = ForgeDirection.SOUTH;
        this.isWorking = false;
        this.energyUsed = 0;
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

    public void setEnergyUsed(int energyUsed)
    {
        this.energyUsed = energyUsed;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        this.slots = new ItemStack[1];

        NBTTagList nbttaglist = nbtTagCompound.getTagList("teNBT", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < 1)
            {
                this.slots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

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
        if (nbtTagCompound.hasKey("teEnergyUsed"))
        {
            this.energyUsed = nbtTagCompound.getInteger("teEnergyUsed");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < 1; ++currentIndex)
        {
            if (slots[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                slots[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }

        nbtTagCompound.setTag("teNBT", tagList);
        nbtTagCompound.setByte("teDirection", (byte) this.orientation.ordinal());
        nbtTagCompound.setBoolean("teWorking", this.isWorking);
        nbtTagCompound.setInteger("teEnergyStored", this.storedEnergy);
        nbtTagCompound.setInteger("teEnergyUsed", this.energyUsed);
    }

    /*@Override
    public Packet getDescriptionPacket()
    {
        //return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityCobbleGenerator(this));
    }*/

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.slots[slot];
    }

    @Override
    public ItemStack decrStackSize(int i, int j)
    {
        ItemStack itemStack = getStackInSlot(i);
        if(itemStack != null)
        {
            if(itemStack.stackSize <= j)
            {
                setInventorySlotContents(i, null);
            }
            else
            {
                itemStack = this.slots[i].splitStack(j);

                if(itemStack.stackSize == 0)
                {
                    setInventorySlotContents(i, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }


    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        this.slots[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
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
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;
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
        return new ItemStack(this.getBlockType());
    }

    @Override
    public double getDemandedEnergy()
    {
        return storedEnergy < MAX_STORED_ENERGY ? MAX_VOLTAGE : 0;
    }

    @Override
    public int getSinkTier()
    {
        return 4;
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

        if(storedEnergy < runningEUPerTick)
        {
            return;
        }

        //this.energyUsed += 3;
        this.storedEnergy -= this.runningEUPerTick;

        addItemStacktoOutput();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        markDirty();
    }

    public int getScaledEnergy()
    {
        return storedEnergy * 13 / MAX_STORED_ENERGY;
    }

    public int getScaledProgress()
    {
        return energyUsed * 22 / 1000;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return side == ForgeDirection.UP.ordinal() ? new int[]{} : new int[]{0};
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side)
    {
        return true;
    }

    public void addItemStacktoOutput()
    {
        ItemStack cobbleStack =  new ItemStack(Blocks.cobblestone);
        int maxStackSize = Math.min(getInventoryStackLimit(), cobbleStack.getMaxStackSize());

        if(this.slots[0] == null)
        {
            this.slots[0] = cobbleStack.copy();
        }
        else if(this.slots[0].getItem() == cobbleStack.getItem())
        {
            if(slots[0].stackSize < maxStackSize)
            {
                this.slots[0].stackSize += 64;
            }
        }
        else
        {
            return;
        }
    }

}
