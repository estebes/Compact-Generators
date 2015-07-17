package com.estebes.compactic2generators.gui;

import com.estebes.compactic2generators.inventory.ContainerCobbleGenerator;
import com.estebes.compactic2generators.inventory.ContainerPCBAssembler;
import com.estebes.compactic2generators.inventory.ContainerSimpleGenerator;
import com.estebes.compactic2generators.inventory.ContainerTreeHarvester;
import com.estebes.compactic2generators.tileentity.*;
import com.estebes.compactic2generators.tileentity.machine.TileEntityPCBAssembler;
import com.estebes.compactic2generators.tileentity.machine.TileEntityTreeHarvester;
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
            TileEntityCobbleMachine tileEntityCobbleMachine = (TileEntityCobbleMachine) world.getTileEntity(x, y ,z);
            return new ContainerCobbleGenerator(player.inventory, new TileEntityCobbleGenerator());
        }

        if(ID == 2)
        {
            TileEntityPCBAssembler tileEntityPCBAssembler = (TileEntityPCBAssembler) world.getTileEntity(x, y ,z);
            return new ContainerPCBAssembler(player.inventory, tileEntityPCBAssembler);
        }

        if(ID == 3)
        {
            TileEntityTreeHarvester tileEntityTreeHarvester = (TileEntityTreeHarvester) world.getTileEntity(x, y ,z);
            return new ContainerTreeHarvester(player.inventory, tileEntityTreeHarvester);
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
            TileEntityCobbleMachine tileEntityCobbleMachine = (TileEntityCobbleMachine) world.getTileEntity(x, y ,z);
            return new GuiCobbleGenerator(player.inventory, tileEntityCobbleMachine);
        }

        if(ID == 2)
        {
            TileEntityPCBAssembler tileEntityPCBAssembler = (TileEntityPCBAssembler) world.getTileEntity(x, y ,z);
            return new GuiPCBAssembler(player.inventory, tileEntityPCBAssembler);
        }

        if(ID == 3)
        {
            TileEntityTreeHarvester tileEntityTreeHarvester = (TileEntityTreeHarvester) world.getTileEntity(x, y ,z);
            return new GuiTreeHarvester(player.inventory, tileEntityTreeHarvester);
        }

        return null;
    }
}
