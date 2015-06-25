package com.estebes.compactic2generators.block;

import com.estebes.compactic2generators.CompactIC2Genenators;
import com.estebes.compactic2generators.reference.Reference;
import com.estebes.compactic2generators.tileentity.TileEntityPCBAssembler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class PCBAssembler extends BlockContainer
{
    private boolean isWorking = false;

    public PCBAssembler()
    {
        super(Material.iron);
        this.setHardness(3.0F);
        this.setStepSound(soundTypeMetal);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        this.isWorking = false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityPCBAssembler();
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(Reference.LOWERCASE_MOD_ID + ":" + "SimpleParticles");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.blockIcon;
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
        if (entityLiving == null)
        {
            return;
        }

        {
            int direction = 0;
            int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
            if (facing == 0)
            {
                direction = ForgeDirection.NORTH.ordinal();
            }
            else if (facing == 1)
            {
                direction = ForgeDirection.EAST.ordinal();
            }
            else if (facing == 2)
            {
                direction = ForgeDirection.SOUTH.ordinal();
            }
            else if (facing == 3)
            {
                direction = ForgeDirection.WEST.ordinal();
            }
            ((TileEntityPCBAssembler) world.getTileEntity(x, y, z)).setOrientation(direction);
        }
    }

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
        TileEntity tileEntityPCBAssembler = world.getTileEntity(x, y, z);
        if (tileEntityPCBAssembler != null && tileEntityPCBAssembler instanceof TileEntityPCBAssembler)
        {
            player.openGui(CompactIC2Genenators.instance, 2, world, x, y, z);
        }
        return true;
    }
}