package com.estebes.compactic2generators.inventory;

import com.estebes.compactic2generators.tileentity.machine.TileEntityPCBAssembler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class ContainerPCBAssembler extends Container
{
    private TileEntityPCBAssembler tileEntityPCBAssembler;

    public int energyPixels;
    public int progressPixels;

    public ContainerPCBAssembler(InventoryPlayer inventoryPlayer, TileEntityPCBAssembler tileEntityPCBAssembler)
    {
        this.tileEntityPCBAssembler = tileEntityPCBAssembler;
        this.addSlotToContainer(new SlotCobbleGenerator(tileEntityPCBAssembler, 0, 106, 24));
        bindPlayerInventory(inventoryPlayer);
    }

    private void bindPlayerInventory(InventoryPlayer inventoryPlayer)
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
        return this.tileEntityPCBAssembler.isUseableByPlayer(player);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        this.energyPixels = tileEntityPCBAssembler.getScaledEnergy();
        this.progressPixels = tileEntityPCBAssembler.getScaledProgress();

        for (Object crafter : this.crafters)
        {
            ICrafting icrafting = (ICrafting) crafter;
            if(this.energyPixels != this.tileEntityPCBAssembler.energyPixels)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntityPCBAssembler.energyPixels);
            }
            if(this.progressPixels != this.tileEntityPCBAssembler.progressPixels)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntityPCBAssembler.progressPixels);
            }
        }

        this.energyPixels = this.tileEntityPCBAssembler.energyPixels;
        this.progressPixels = this.tileEntityPCBAssembler.progressPixels;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        switch(valueType)
        {
            case 0: this.tileEntityPCBAssembler.energyPixels = updatedValue;
                break;
            case 1: this.tileEntityPCBAssembler.progressPixels = updatedValue;
                break;
        }
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

            if (slotIndex == 0)
            {
                if (!this.mergeItemStack(slotItemStack, 1, inventorySlots.size(), false))
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

    private class SlotCobbleGenerator extends Slot
    {
        public SlotCobbleGenerator(IInventory inventory, int slotIndex, int x, int y)
        {
            super(inventory, slotIndex, x, y);
        }

        @Override
        public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
        {
            super.onPickupFromSlot(entityPlayer, itemStack);
            FMLCommonHandler.instance().firePlayerCraftingEvent(entityPlayer, itemStack, inventory);
        }
        @Override
        public boolean isItemValid(ItemStack itemStack)
        {
            return false;
        }
    }
}

