package com.estebes.compactic2generators.inventory;

import com.estebes.compactic2generators.inventory.slot.SlotModule;
import com.estebes.compactic2generators.tileentity.machine.TileEntityElectricItemBuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerElectricItemBuffer extends Container
{
    // Variables
    private TileEntityElectricItemBuffer tileEntityElectricItemBuffer;


    // Constructor
    public ContainerElectricItemBuffer(IInventory inventoryPlayer, TileEntityElectricItemBuffer tileEntityElectricItemBuffer)
    {
        this.tileEntityElectricItemBuffer = tileEntityElectricItemBuffer;
        bindOutputInventory(tileEntityElectricItemBuffer);
        bindPlayerInventory(inventoryPlayer);
    }


    // Internal Inventory
    private void bindOutputInventory(IInventory inventory)
    {
        int slotIndex = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                addSlotToContainer(new Slot(tileEntityElectricItemBuffer, slotIndex, 44 + j * 18, 22 + i * 18));
                slotIndex++;
            }
        }
    }


    // Player Inventory
    private void bindPlayerInventory(IInventory inventoryPlayer)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));

        }
    }


    // Stuff
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tileEntityElectricItemBuffer.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack())
        {
            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            if (!this.mergeItemStack(slotItemStack, 0, inventorySlots.size(), false))
            {
                return null;
            }

            if (slotItemStack.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }
}
