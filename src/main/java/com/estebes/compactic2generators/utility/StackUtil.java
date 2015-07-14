package com.estebes.compactic2generators.utility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

// Rip from ic2 source
public class StackUtil
{
    public static Block getBlock(ItemStack stack)
    {
        Item item = stack.getItem();
        if ((item instanceof ItemBlock))
        {
            return ((ItemBlock)item).field_150939_a;
        }
        return null;
    }

    public static boolean equals(Block block, ItemStack stack)
    {
        return block == getBlock(stack);
    }
}
