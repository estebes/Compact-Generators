package com.estebes.compactic2generators.tileentity.machine;

import com.estebes.compactic2generators.network.PacketHandler;
import com.estebes.compactic2generators.network.message.MessageTileEntityElectricItemBuffer;
import com.estebes.compactic2generators.tileentity.TileEntityBaseMachine;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyConductor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityElectricItemBuffer extends TileEntityBaseMachine implements IEnergyConductor, ISidedInventory
{
    // Variables
    private boolean enetChecker;
    private ItemStack[] machineInventory;


    // Constructor
    public TileEntityElectricItemBuffer()
    {
        super();
        this.machineInventory = new ItemStack[15];
    }


    // NBT Stuff
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        NBTTagList nbttaglist = nbtTagCompound.getTagList("teNBT", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < machineInventory.length)
            {
                this.machineInventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < machineInventory.length; ++currentIndex)
        {
            if (machineInventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                machineInventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }

        nbtTagCompound.setTag("teNBT", tagList);
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



    @Override
    public double getConductionLoss()
    {
        return 0;
    }

    @Override
    public double getInsulationEnergyAbsorption()
    {
        return 8192;
    }

    @Override
    public double getInsulationBreakdownEnergy()
    {
        return 8192;
    }

    @Override
    public double getConductorBreakdownEnergy()
    {
        return 8192;
    }

    @Override
    public void removeInsulation()
    {

    }

    @Override
    public void removeConductor()
    {

    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction)
    {
        return true;
    }

    @Override
    public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction)
    {
        return true;
    }


    // Inventory Stuff
    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        int[] indexArray = new int[machineInventory.length];
        for(int slotIndex = 0; slotIndex < machineInventory.length; slotIndex++)
        {
            indexArray[slotIndex] = slotIndex;
        }
        return indexArray;
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side)
    {
        return true;
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side)
    {
        return true;
    }

    @Override
    public int getSizeInventory()
    {
        return machineInventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return this.machineInventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotOrigin, int slotDestination)
    {
        ItemStack itemStack = getStackInSlot(slotOrigin);
        if(itemStack != null)
        {
            if(itemStack.stackSize <= slotDestination)
            {
                setInventorySlotContents(slotOrigin, null);
            }
            else
            {
                itemStack = this.machineInventory[slotOrigin].splitStack(slotDestination);

                if(itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotOrigin, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        this.machineInventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
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
        return true;
    }

    @Override
    public void openInventory()
    {
        //
    }

    @Override
    public void closeInventory()
    {
        //
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return true;
    }


    // Network Stuff
    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityElectricItemBuffer(this));
    }
}
