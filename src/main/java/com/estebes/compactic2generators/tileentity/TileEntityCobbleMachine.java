package com.estebes.compactic2generators.tileentity;

import com.estebes.compactic2generators.network.PacketHandler;
import com.estebes.compactic2generators.network.message.MessageTileEntityCobbleGenerator;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCobbleMachine extends TileEntityBaseMachine implements ISidedInventory, IEnergySink
{
    // Misc Variables
    private final int machineTier;
    private int tickCounter;

    // Energy Variables
    private final int MAX_STORED_ENERGY;
    private final int MAX_VOLTAGE;
    private final int ENERGY_COST_PER_TICK;
    private int STORED_ENERGY;
    private boolean ENET_CHECKER;

    // Slot Variables
    private ItemStack[] machineInventory;

    public TileEntityCobbleMachine(int machineTier)
    {
        this.machineInventory = new ItemStack[1];
        this.machineTier = machineTier;
        this.MAX_STORED_ENERGY = 4000 * (int) Math.pow(4, machineTier - 1);
        this.MAX_VOLTAGE = 32 * (int) Math.pow(4, machineTier - 1);
        this.ENERGY_COST_PER_TICK = 2 * (int) Math.pow(8, machineTier - 1);
        this.STORED_ENERGY = 0;
        this.tickCounter = 0;
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
        return this.machineTier;
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


    // ISided Inventory Stuff
    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_)
    {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
    {
        return false;
    }

    @Override
    public int getSizeInventory()
    {
        return this.machineInventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_)
    {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_)
    {

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
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return false;
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
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return false;
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

        if(this.STORED_ENERGY < this.ENERGY_COST_PER_TICK)
        {
            return;
        }

        this.tickCounter++;
        this.STORED_ENERGY -= this.ENERGY_COST_PER_TICK;

        if(this.tickCounter >= (int) (512 / this.ENERGY_COST_PER_TICK))
        {
            addItemStackToOutput();
            this.tickCounter = 0;
        }

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        markDirty();
    }

    protected void addItemStackToOutput()
    {
        ItemStack cobbleStack =  new ItemStack(Blocks.cobblestone);
        int maxStackSize = Math.min(getInventoryStackLimit(), cobbleStack.getMaxStackSize());

        if(this.machineInventory[0] == null)
        {
            this.machineInventory[0] = cobbleStack.copy();
        }
        else if(this.machineInventory[0].getItem() == cobbleStack.getItem())
        {
            if(this.machineInventory[0].stackSize < maxStackSize)
            {
                if(this.machineTier == 4)
                {
                    this.machineInventory[0].stackSize += 64;
                }
                else
                {
                    this.machineInventory[0].stackSize += 1;
                }
            }
        }
        else
        {
            return;
        }
    }


    // Network Stuff
    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityCobbleGenerator(null));
    }
}
