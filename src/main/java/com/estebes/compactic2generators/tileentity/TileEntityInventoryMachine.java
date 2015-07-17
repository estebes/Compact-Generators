package com.estebes.compactic2generators.tileentity;

import com.estebes.compactic2generators.network.PacketHandler;
import com.estebes.compactic2generators.network.message.MessageTileEntityInventoryMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;

public class TileEntityInventoryMachine extends TileEntityBaseMachine implements ISidedInventory
{
    // Inventory Vars
    private ItemStack[] machineInventory;


    // Constructor
    public TileEntityInventoryMachine(int inventorySize)
    {
        super();
        this.machineInventory = new ItemStack[inventorySize];
    }


    // NBT Stuff
    /*@Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey("tileEntityOrientation"))
        {
            //this.orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte("tileEntityOrientation"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        //nbtTagCompound.setByte("tileEntityOrientation", (byte) this.orientation.ordinal());
    }*/


    // ENET Stuff


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
        return false;
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
        // TODO
    }

    @Override
    public void closeInventory()
    {
        // TODO
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return false;
    }


    // Network Stuff
    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityInventoryMachine(this));
    }
}
