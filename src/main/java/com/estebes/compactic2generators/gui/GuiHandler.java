package com.estebes.compactic2generators.gui;

import com.estebes.compactic2generators.inventory.ContainerCobbleGenerator;
import com.estebes.compactic2generators.inventory.ContainerSimpleGenerator;
import com.estebes.compactic2generators.tileentity.TileEntityCobbleGenerator;
import com.estebes.compactic2generators.tileentity.TileEntitySimpleGenerator;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == 0)
        {
            TileEntitySimpleGenerator tileEntitySimpleGenerator = (TileEntitySimpleGenerator) world.getTileEntity(x, y ,z);
            return new ContainerSimpleGenerator(player.inventory, tileEntitySimpleGenerator);
        }

        if(ID == 1)
        {
            TileEntityCobbleGenerator tileEntityCobbleGenerator = (TileEntityCobbleGenerator) world.getTileEntity(x, y ,z);
            return new ContainerCobbleGenerator(player.inventory, tileEntityCobbleGenerator);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == 0)
        {
            TileEntitySimpleGenerator tileEntitySimpleGenerator = (TileEntitySimpleGenerator) world.getTileEntity(x, y ,z);
            return new GuiSimpleGenerator(player.inventory, tileEntitySimpleGenerator);
        }

        if(ID == 1)
        {
            TileEntityCobbleGenerator tileEntityCobbleGenerator = (TileEntityCobbleGenerator) world.getTileEntity(x, y ,z);
            return new GuiCobbleGenerator(player.inventory, tileEntityCobbleGenerator);
        }

        return null;
    }
}
