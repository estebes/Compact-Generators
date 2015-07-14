package com.estebes.compactic2generators.block;

import com.estebes.compactic2generators.CompactIC2Genenators;
import com.estebes.compactic2generators.data.CobbleMachineTier;
import com.estebes.compactic2generators.reference.Reference;
import com.estebes.compactic2generators.tileentity.TileEntityCobbleMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.IC2Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockBaseMachine extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private IIcon iconFront;


    public BlockBaseMachine()
    {
        super(Material.iron);
        this.setBlockName("Cobble Machine");
        this.setHardness(5.0F);
        this.setStepSound(soundTypeMetal);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityCobbleMachine(metaData);
    }

    // Block render stuff
    /*@Override
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
    }*/

    // Random stuff to try to have particle effects on break
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(Reference.LOWERCASE_MOD_ID + ":" + "Mark3MachineCasing");
        this.iconFront = iconRegister.registerIcon(Reference.LOWERCASE_MOD_ID + ":" + "Mark4MachineCasing");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return (side == 3 ? this.iconFront : this.blockIcon);
    }

    // When the block is placed
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
        if (entityLiving == null)
        {
            return;
        }

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
        ((TileEntityCobbleMachine) world.getTileEntity(x, y, z)).setOrientation(direction);
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
        TileEntity tileEntityCobbleMachine = world.getTileEntity(x, y, z);
        if (tileEntityCobbleMachine != null && tileEntityCobbleMachine instanceof TileEntityCobbleMachine)
        {
        }
        return true;
    }


    // Drop machine casing on break
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> droppedBlock = new ArrayList<ItemStack>();
        droppedBlock.add(IC2Items.getItem("machine"));
        return droppedBlock;
    }

    // Drop inventory on break
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metaData)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof ISidedInventory))
        {
            return;
        }
        ISidedInventory inventory = (ISidedInventory) tileEntity;
        for (int slot = 0; slot < inventory.getSizeInventory(); slot++)
        {
            ItemStack itemStack = inventory.getStackInSlot(slot);
            if (itemStack != null && itemStack.stackSize > 0)
            {
                Random rand = new Random();
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;
                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());
                if (itemStack.hasTagCompound())
                {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }
                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
        super.breakBlock(world, x, y, z, block, metaData);
    }

    @Override
    public int damageDropped (int metaData)
    {
        return metaData;
    }


    // Sub-Blocks stuff
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List itemList)
    {
        for (int i = 1; i < 5; i++)
        {
            itemList.add(new ItemStack(this, 1, i));
        }
    }
}
