package com.estebes.compactic2generators.network.message;

import com.estebes.compactic2generators.tileentity.machine.TileEntityEnergyItemBuffer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityEnergyItemBuffer implements IMessage, IMessageHandler<MessageTileEntityEnergyItemBuffer, IMessage>
{
    // Variables
    private int x, y, z;
    private byte orientation;
    private int storedEnergy;


    // Constructor
    public MessageTileEntityEnergyItemBuffer()
    {
    }

    public MessageTileEntityEnergyItemBuffer(TileEntityEnergyItemBuffer tileEntityEnergyItemBuffer)
    {
        this.orientation = (byte) tileEntityEnergyItemBuffer.getOrientation().ordinal();
        this.storedEnergy = tileEntityEnergyItemBuffer.getStoredEnergy();
    }


    // Buffer Stuff
    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        orientation = buf.readByte();
        storedEnergy = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeInt(storedEnergy);
    }

    @Override
    public IMessage onMessage(MessageTileEntityEnergyItemBuffer message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
        if (tileEntity instanceof TileEntityEnergyItemBuffer)
        {
            ((TileEntityEnergyItemBuffer) tileEntity).setOrientation(message.orientation);
            ((TileEntityEnergyItemBuffer) tileEntity).setStoredEnergy(message.storedEnergy);
        }
        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityEnergyItemBuffer - x:%s, y:%s, z:%s, orientation:%s, storedEnergy:%s",
                x, y, z, orientation, storedEnergy);
    }
}
