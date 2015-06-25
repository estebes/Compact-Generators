package com.estebes.compactic2generators.inventory;

import com.estebes.compactic2generators.tileentity.TileEntitySimpleGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerSimpleGenerator extends Container
{
    private TileEntitySimpleGenerator tileEntitySimpleGenerator;

    public ContainerSimpleGenerator(IInventory inventoryPlayer, TileEntitySimpleGenerator tileEntitySimpleGenerator)
    {
        this.tileEntitySimpleGenerator = tileEntitySimpleGenerator;
        bindPlayerInventory(inventoryPlayer);
    }

    private void bindPlayerInventory(IInventory inventoryPlayer)
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                        8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }
}
