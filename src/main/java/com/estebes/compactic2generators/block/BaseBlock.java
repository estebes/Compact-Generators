package com.estebes.compactic2generators.block;

import com.estebes.compactic2generators.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BaseBlock extends Block
{
    public BaseBlock()
    {
        super(Material.iron);
        this.setHardness(5.0F);
        this.setStepSound(soundTypeMetal);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(Reference.LOWERCASE_MOD_ID + ":" + "Mark3MachineCasing");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.blockIcon;
    }
}
