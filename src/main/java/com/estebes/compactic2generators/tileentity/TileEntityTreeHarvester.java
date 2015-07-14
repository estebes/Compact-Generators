package com.estebes.compactic2generators.tileentity;

import com.estebes.compactic2generators.network.PacketHandler;
import com.estebes.compactic2generators.network.message.MessageTileEntityTreeHarvester;
import com.estebes.compactic2generators.utility.StackUtil;
import cpw.mods.fml.client.FMLClientHandler;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.item.IC2Items;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityTreeHarvester extends TileEntityBaseMachine implements IEnergySink, ISidedInventory
{
    // Misc Variables
    private int tickCounter;
    private static final int harvestMode = 2;
    private int currentX = -5, currentY = 0, currentZ = -5;

    // Energy Variables
    private final int MAX_STORED_ENERGY;
    private final int MAX_VOLTAGE;
    private final int ENERGY_COST_PER_TICK;
    private int STORED_ENERGY;
    private boolean ENET_CHECKER;

    // Slot Variables
    private static final int moduleSlot = 0;
    private static final int powerSlot = 1;
    private ItemStack[] machineInventory;

    // Constructor
    public TileEntityTreeHarvester()
    {
        super();
        this.MAX_STORED_ENERGY = 10000;
        this.MAX_VOLTAGE = 32;
        this.ENERGY_COST_PER_TICK = 2;
        this.STORED_ENERGY = 0;
        this.tickCounter = 0;
        this.machineInventory = new ItemStack[16];
    }


    // NBT Stuff
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        NBTTagList nbttaglist = nbtTagCompound.getTagList("teNBT", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < machineInventory.length)
            {
                this.machineInventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        if (nbtTagCompound.hasKey("teEnergyStored"))
        {
            this.STORED_ENERGY = nbtTagCompound.getInteger("teEnergyStored");
        }
        if (nbtTagCompound.hasKey("teTickCounter"))
        {
            this.tickCounter = nbtTagCompound.getInteger("teTickCounter");
        }
        if (nbtTagCompound.hasKey("teScanX"))
        {
            this.currentX = nbtTagCompound.getInteger("teScanX");
        }
        if (nbtTagCompound.hasKey("teScanY"))
        {
            this.currentY = nbtTagCompound.getInteger("teScanY");
        }
        if (nbtTagCompound.hasKey("teScanZ"))
        {
            this.currentZ = nbtTagCompound.getInteger("teScanZ");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < machineInventory.length; ++currentIndex)
        {
            if (machineInventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                machineInventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }

        nbtTagCompound.setTag("teNBT", tagList);
        nbtTagCompound.setInteger("teEnergyStored", STORED_ENERGY);
        nbtTagCompound.setInteger("teTickCounter", tickCounter);
        nbtTagCompound.setInteger("teScanX", currentX);
        nbtTagCompound.setInteger("teScanY", currentY);
        nbtTagCompound.setInteger("teScanZ", currentZ);
    }


    // IEnergy Sink Stuff
    @Override
    public double getDemandedEnergy()
    {
        return STORED_ENERGY < MAX_STORED_ENERGY ? MAX_VOLTAGE : 0;
    }

    @Override
    public int getSinkTier()
    {
        return 1;
    }

    @Override
    public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage)
    {
        if(STORED_ENERGY >= MAX_STORED_ENERGY)
        {
            return amount;
        }

        STORED_ENERGY += Math.min(MAX_VOLTAGE, (int)amount);
        return amount - Math.min(MAX_VOLTAGE, (int)amount);
    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction)
    {
        return true;
    }

    // ENET stuff
    @Override
    public void invalidate()
    {
        if(!worldObj.isRemote && this.ENET_CHECKER)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            this.ENET_CHECKER = false;
        }
        super.invalidate();
    }

    @Override
    public void onChunkUnload()
    {
        if(!worldObj.isRemote && this.ENET_CHECKER)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            this.ENET_CHECKER = false;
        }
        super.onChunkUnload();
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if(worldObj.isRemote)
        {
            return;
        }

        if(!this.ENET_CHECKER)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            this.ENET_CHECKER = true;
        }

        if(this.STORED_ENERGY < (this.ENERGY_COST_PER_TICK + 200))
        {
            return;
        }

        this.tickCounter++;
        this.STORED_ENERGY -= this.ENERGY_COST_PER_TICK;

        if(this.tickCounter < 20)
        {
            this.tickCounter++;
            return;
        }

        switch(harvestMode)
        {
            case 1: harvestBlock(this.xCoord, this.yCoord, this.zCoord);
                    break;
            case 2: harvestRubber(this.xCoord + currentX, this.yCoord + currentY, this.zCoord + currentZ);
                    break;
            default: break;
        }

        this.tickCounter = 0;

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        markDirty();
    }


    // Coord Scanning
    protected void scanSquare()
    {
        currentX++;
        if(currentX > 5)
        {
            currentX = -5;
            currentZ++;
            if(currentZ > 5)
            {
                currentZ = -5;
            }
        }
    }


    // Wood Harvesting
    protected void harvestBlock(int x, int y, int z)
    {
        if(isBlockHarvestable(x, y, z))
        {
            FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(x, y, z,
                    this.worldObj.getBlock(x, y, z), worldObj.getBlockMetadata(x, y, z));

            this.worldObj.setBlockToAir(x, y, z);

            if (this.worldObj.getTileEntity(x, y, z) != null)
            {
                this.worldObj.setTileEntity(x, y, z, null);
            }
        }
        else
        {
            ItemStack sapling = new ItemStack(Blocks.sapling, 1, 0);
            sapling.copy().tryPlaceItemIntoWorld(FakePlayerFactory.getMinecraft((WorldServer) getWorldObj()), getWorldObj(), x, y, z, 1, 0, 0, 0);
        }
    }

    protected boolean isBlockHarvestable(int x, int y, int z)
    {
        return this.worldObj.getBlock(x, y, z).isWood(this.worldObj, x, y, z) || this.worldObj.getBlock(x, y, z).isLeaves(this.worldObj, x, y, z) ? true : false;
    }


    // Rubber Harvesting
    protected void harvestRubber(int x, int y, int z)
    {
        int meta = this.worldObj.getBlockMetadata(x, y, z);
        if(isRubberWood(x, y, z))
        {
            if(isRubberHarvestable(x, y, z))
            {
                FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(x, y, z,
                        this.worldObj.getBlock(x, y, z), worldObj.getBlockMetadata(x, y, z));
                this.worldObj.setBlockMetadataWithNotify(x, y, z, meta + 6, 2);
                STORED_ENERGY -= 200;
                addStackToOutput();
            }
            currentY++;
        }
        else
        {
            scanSquare();
            currentY = 0;
        }
    }

    protected boolean isRubberWood(int x, int y, int z)
    {
        Block block = this.worldObj.getBlock(x, y, z);
        if(StackUtil.equals(block, IC2Items.getItem("rubberWood")))
        {
            return true;
        }
        return false;
    }

    protected boolean isRubberHarvestable(int x, int y, int z)
    {
        int meta = this.worldObj.getBlockMetadata(x, y, z);
        Block block = this.worldObj.getBlock(x, y, z);
        if(StackUtil.equals(block, IC2Items.getItem("rubberWood")))
        {
            if (meta >= 2 && meta <= 5)
            {
                return true;
            }
        }
        return false;
    }


    // Slot Management
    protected void addStackToOutput()
    {
        ItemStack itemStack =  IC2Items.getItem("resin");
        int maxStackSize = Math.min(getInventoryStackLimit(), itemStack.getMaxStackSize());

        int slotIndex = 1;

        while(slotIndex < machineInventory.length)
        {
            if (this.machineInventory[slotIndex] == null)
            {
                this.machineInventory[slotIndex] = itemStack.copy();
                return;
            }
            else if (this.machineInventory[slotIndex].getItem() == itemStack.getItem())
            {
                if (machineInventory[slotIndex].stackSize < maxStackSize)
                {
                    this.machineInventory[slotIndex].stackSize += itemStack.stackSize;
                }
                return;
            }
            else
            {
                slotIndex++;
            }
        }
    }

    public int getModuleSlot()
    {
        return moduleSlot;
    }


    // Inventory Stuff
    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        int[] indexArray = new int[machineInventory.length - 1];
        for(int slotIndex = 0; slotIndex < machineInventory.length - 1; slotIndex++)
        {
            indexArray[slotIndex] = slotIndex + 1;
        }
        return indexArray;
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side)
    {
        return slotIndex == getModuleSlot() ? false : true;
    }

    @Override
    public int getSizeInventory()
    {
        return machineInventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return this.machineInventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int i, int j)
    {
        ItemStack itemStack = getStackInSlot(i);
        if(itemStack != null)
        {
            if(itemStack.stackSize <= j)
            {
                setInventorySlotContents(i, null);
            }
            else
            {
                itemStack = this.machineInventory[i].splitStack(j);

                if(itemStack.stackSize == 0)
                {
                    setInventorySlotContents(i, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        this.machineInventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory()
    {
        // TODO
    }

    @Override
    public void closeInventory()
    {
        // TODO
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        return false;
    }


    // Var Access
    public int getStoreEnergy()
    {
        return STORED_ENERGY;
    }

    public void setStoredEnergy(int value)
    {
        STORED_ENERGY = value;
    }

    public int getTickCounter()
    {
        return tickCounter;
    }

    public void setTickCounter(int value)
    {
        tickCounter = value;
    }

    public int[] getScanCoords()
    {
        return new int[]{currentX, currentY, currentZ};
    }

    public void setScanCoords(int[] scanCoords)
    {
        currentX = scanCoords[0];
        currentY = scanCoords[1];
        currentZ = scanCoords[2];
    }

    public int getScaledEnergy()
    {
        return STORED_ENERGY * 13 / MAX_STORED_ENERGY;
    }

    // Network Stuff
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityTreeHarvester(this));
    }
}
