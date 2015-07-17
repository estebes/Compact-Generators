package com.estebes.compactic2generators.inventory;

import com.estebes.compactic2generators.inventory.slot.SlotModule;
import com.estebes.compactic2generators.inventory.slot.SlotTreeHarvester;
import com.estebes.compactic2generators.tileentity.machine.TileEntityTreeHarvester;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTreeHarvester extends Container
{
    private TileEntityTreeHarvester tileEntityTreeHarvester;

    public int energyPixels;

    public ContainerTreeHarvester(IInventory inventoryPlayer, TileEntityTreeHarvester tileEntityTreeHarvester)
    {
        this.tileEntityTreeHarvester = tileEntityTreeHarvester;
        this.addSlotToContainer(new SlotModule(tileEntityTreeHarvester, tileEntityTreeHarvester.getModuleSlot(), 15, 40));
        bindOutputInventory(tileEntityTreeHarvester);
        bindPlayerInventory(inventoryPlayer);
    }

    private void bindOutputInventory(IInventory inventory)
    {
        int slotIndex = 1;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                addSlotToContainer(new SlotTreeHarvester(tileEntityTreeHarvester, slotIndex, 44 + j * 18, 22 + i * 18));
                slotIndex++;
            }
        }
    }

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

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tileEntityTreeHarvester.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotIndex);
        SlotModule slotModule = (SlotModule) this.inventorySlots.get(0);
        if (slot != null && slot.getHasStack())
        {
            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            if (slotIndex == 0)
            {
                if (!this.mergeItemStack(slotItemStack, tileEntityTreeHarvester.getSizeInventory(), inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else if (slotModule.isItemValid(itemStack))
            {
                if (!this.mergeItemStack(slotItemStack, 0, tileEntityTreeHarvester.getSizeInventory(), false))
                {
                    return null;
                }
            }
            else
            {
                if (!this.mergeItemStack(slotItemStack, inventorySlots.size(), 0, false))
                {
                    return null;
                }
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

   /* @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        this.energyPixels = tileEntityTreeHarvester.getScaledEnergy();

        for (Object crafter : this.crafters)
        {
            ICrafting icrafting = (ICrafting) crafter;
            if(this.energyPixels != tileEntityTreeHarvester.energyPixels)
            {
                icrafting.sendProgressBarUpdate(this, 0, tileEntityTreeHarvester.energyPixels);
            }
        }

        this.energyPixels = tileEntityTreeHarvester.energyPixels;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        switch(valueType)
        {
            case 0: tileEntityTreeHarvester.energyPixels = updatedValue;
                break;
        }
    }*/
}
