package com.estebes.compactic2generators.inventory;

import com.estebes.compactic2generators.tileentity.TileEntityCobbleGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerCobbleGenerator extends Container
{
    private TileEntityCobbleGenerator tileEntityCobbleGenerator;

    public ContainerCobbleGenerator(InventoryPlayer inventoryPlayer, TileEntityCobbleGenerator tileEntityCobbleGenerator)
    {
        this.tileEntityCobbleGenerator = tileEntityCobbleGenerator;
        bindPlayerInventory(inventoryPlayer);
    }

    private void bindPlayerInventory(InventoryPlayer inventoryPlayer)
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
        return this.tileEntityCobbleGenerator.isUseableByPlayer(player);
    }
}

