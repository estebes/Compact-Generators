package com.estebes.compactic2generators.network.message;

import com.estebes.compactic2generators.tileentity.TileEntityCobbleMachine;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityCobbleMachine implements IMessage, IMessageHandler<MessageTileEntityCobbleMachine, IMessage>
{
    public int x, y, z;
    public byte orientation;
    public boolean isWorking;
    public int storedEnergy;
    public int energyUsed;
    public byte stackSize, stackMeta;

    public MessageTileEntityCobbleMachine()
    {
    }

    public MessageTileEntityCobbleMachine(TileEntityCobbleMachine tileEntityCobbleMachine)
    {
        /*this.x = tileEntityCobbleGenerator.xCoord;
        this.y = tileEntityCobbleGenerator.yCoord;
        this.z = tileEntityCobbleGenerator.zCoord;
        this.orientation = (byte) tileEntityCobbleGenerator.getOrientation().ordinal();
        this.isWorking = tileEntityCobbleGenerator.getWorkingState();
        this.storedEnergy = tileEntityCobbleGenerator.getEnergyStored();
        this.energyUsed = tileEntityCobbleGenerator.energyUsed;
        this.stackSize = tileEntityCobbleGenerator.stackSize;
        this.stackMeta = tileEntityCobbleGenerator.stackMeta;*/
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orientation = buf.readByte();
        this.isWorking = buf.readBoolean();
        this.storedEnergy = buf.readInt();
        this.energyUsed = buf.readInt();
        this.stackSize = buf.readByte();
        this.stackMeta = buf.readByte();

    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeBoolean(isWorking);
        buf.writeInt(storedEnergy);
        buf.writeInt(energyUsed);
        buf.writeByte(stackSize);
        buf.writeByte(stackMeta);
    }

    @Override
    public IMessage onMessage(MessageTileEntityCobbleMachine message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
        if (tileEntity instanceof TileEntityCobbleMachine)
        {
            /*((TileEntityCobbleGenerator) tileEntity).setOrientation(message.orientation);
            ((TileEntityCobbleGenerator) tileEntity).setWorkingState(message.isWorking);
            ((TileEntityCobbleGenerator) tileEntity).setStoredEnergy(message.storedEnergy);
            ((TileEntityCobbleGenerator) tileEntity).setEnergyUsed(message.energyUsed);
            ((TileEntityCobbleGenerator) tileEntity).stackSize = message.stackSize;
            ((TileEntityCobbleGenerator) tileEntity).stackMeta = message.stackMeta;*/
        }
        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntitySimpleGenerator - x:%s, y:%s, z:%s, orientation:%s, isWorking:%s, storedEnergy:%s, energyUsed:%s, stackSize: %s, stackMeta: %s", x, y, z, orientation, isWorking, storedEnergy, energyUsed, stackSize, stackMeta);
    }
}
