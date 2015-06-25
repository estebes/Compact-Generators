package com.estebes.compactic2generators.network.message;

import com.estebes.compactic2generators.tileentity.TileEntityCobbleGenerator;
import com.estebes.compactic2generators.tileentity.TileEntityPCBAssembler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityPCBAssembler implements IMessage, IMessageHandler<MessageTileEntityPCBAssembler, IMessage>
{
    public int x, y, z;
    public byte orientation;
    public boolean isWorking;
    public int storedEnergy;
    public int energyUsed;
    public byte stackSize, stackMeta;

    public MessageTileEntityPCBAssembler()
    {
    }

    public MessageTileEntityPCBAssembler(TileEntityPCBAssembler tileEntityPCBAssembler)
    {
        this.x = tileEntityPCBAssembler.xCoord;
        this.y = tileEntityPCBAssembler.yCoord;
        this.z = tileEntityPCBAssembler.zCoord;
        this.orientation = (byte) tileEntityPCBAssembler.getOrientation().ordinal();
        this.isWorking = tileEntityPCBAssembler.getWorkingState();
        this.storedEnergy = tileEntityPCBAssembler.getEnergyStored();
        this.energyUsed = tileEntityPCBAssembler.energyUsed;
        this.stackSize = tileEntityPCBAssembler.stackSize;
        this.stackMeta = tileEntityPCBAssembler.stackMeta;
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
    public IMessage onMessage(MessageTileEntityPCBAssembler message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
        if (tileEntity instanceof TileEntityPCBAssembler)
        {
            ((TileEntityPCBAssembler) tileEntity).setOrientation(message.orientation);
            ((TileEntityPCBAssembler) tileEntity).setWorkingState(message.isWorking);
            ((TileEntityPCBAssembler) tileEntity).setStoredEnergy(message.storedEnergy);
            ((TileEntityPCBAssembler) tileEntity).setEnergyUsed(message.energyUsed);
            ((TileEntityPCBAssembler) tileEntity).stackSize = message.stackSize;
            ((TileEntityPCBAssembler) tileEntity).stackMeta = message.stackMeta;
        }
        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityPCBAssembler - x:%s, y:%s, z:%s, orientation:%s, isWorking:%s, storedEnergy:%s, energyUsed:%s, stackSize: %s, stackMeta: %s", x, y, z, orientation, isWorking, storedEnergy, energyUsed, stackSize, stackMeta);
    }
}
