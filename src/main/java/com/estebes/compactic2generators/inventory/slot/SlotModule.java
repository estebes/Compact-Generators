package com.estebes.compactic2generators.inventory.slot;

import com.estebes.compactic2generators.init.ItemInit;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotModule extends Slot
{
    public SlotModule(IInventory inventory, int slotIndex, int x, int y)
    {
        super(inventory, slotIndex, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return itemStack.getItem() == ItemInit.itemRubberModule;
    }

    @Override
    public int getSlotStackLimit()
    {
        return 1;
    }
}
