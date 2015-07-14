package com.estebes.compactic2generators.inventory.slot;

import com.estebes.compactic2generators.init.ItemInit;
import ic2.api.item.IC2Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTreeHarvester extends Slot
{
    public SlotTreeHarvester(IInventory inventory, int slotIndex, int x, int y)
    {
        super(inventory, slotIndex, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return false;
    }
}
