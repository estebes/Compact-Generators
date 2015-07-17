package com.estebes.compactic2generators.block.machine;

import com.estebes.compactic2generators.CompactIC2Genenators;
import com.estebes.compactic2generators.reference.Reference;
import com.estebes.compactic2generators.tileentity.machine.TileEntityElectricItemBuffer;
import com.estebes.compactic2generators.tileentity.machine.TileEntityEnergyItemBuffer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by estebes on 17/07/2015.
 */
public class ElectricItemBuffer extends BlockContainer
{
    public ElectricItemBuffer()
    {
        super(Material.iron);
        this.setHardness(5.0F);
        this.setStepSound(soundTypeMetal);
        this.setBlockName("ElectricItemBuffer");
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(Reference.LOWERCASE_MOD_ID + ":" + "energyitembuffer");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.blockIcon;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityElectricItemBuffer();
    }


    // Open GUI
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int s, float f1, float f2, float f3)
    {
        if (player.isSneaking())
        {
            return false;
        }

        if (world.isRemote)
        {
            return true;
        }

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity != null && tileEntity instanceof TileEntityElectricItemBuffer)
        {
            player.openGui(CompactIC2Genenators.instance, 5, world, x, y, z);
        }
        return true;
    }
}
