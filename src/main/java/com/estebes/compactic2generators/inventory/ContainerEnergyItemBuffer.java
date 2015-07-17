package com.estebes.compactic2generators.inventory;

import com.estebes.compactic2generators.tileentity.machine.TileEntityEnergyItemBuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerEnergyItemBuffer extends Container
{
    // Variables
    private TileEntityEnergyItemBuffer tileEntityEnergyItemBuffer;


    // Constructor
    public ContainerEnergyItemBuffer(IInventory inventoryPlayer, TileEntityEnergyItemBuffer tileEntityEnergyItemBuffer)
    {
        this.tileEntityEnergyItemBuffer = tileEntityEnergyItemBuffer;
        //this.addSlotToContainer(new SlotModule(tileEntityTreeHarvester, tileEntityTreeHarvester.getModuleSlot(), 15, 40));
        //bindOutputInventory(tileEntityTreeHarvester);
        bindPlayerInventory(inventoryPlayer);
    }


    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tileEntityEnergyItemBuffer.isUseableByPlayer(player);
    }


    //
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
}
