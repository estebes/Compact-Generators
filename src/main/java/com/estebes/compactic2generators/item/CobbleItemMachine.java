package com.estebes.compactic2generators.item;

import com.estebes.compactic2generators.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class CobbleItemMachine extends ItemBlockWithMetadata
{
    public CobbleItemMachine(Block machineTier)
    {
        super(machineTier, BlockInit.cobbleMachine);
        setHasSubtypes(true);
    }

    public int getMetadata(int i) {
        return i;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return "tile.cobblemachine:" + itemstack.getItemDamage();
    }
}
