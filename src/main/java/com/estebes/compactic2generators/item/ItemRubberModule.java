package com.estebes.compactic2generators.item;

import com.estebes.compactic2generators.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemRubberModule extends Item
{
    public ItemRubberModule()
    {
        maxStackSize = 1;
        setCreativeTab(CreativeTabs.tabRedstone);
        setUnlocalizedName("lol");
        setTextureName(Reference.LOWERCASE_MOD_ID + ":" + "ItemRubberDetector");
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(Reference.LOWERCASE_MOD_ID + ":" + "ItemRubberDetector");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.itemIcon;
    }
}
