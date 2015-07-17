package com.estebes.compactic2generators.network.message;

import com.estebes.compactic2generators.tileentity.machine.TileEntityElectricItemBuffer;
import com.estebes.compactic2generators.tileentity.machine.TileEntityEnergyItemBuffer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityElectricItemBuffer implements IMessage, IMessageHandler<MessageTileEntityElectricItemBuffer, IMessage>
{
    // Variables
    private int x, y, z;
    private byte orientation;


    // Constructor
    public MessageTileEntityElectricItemBuffer()
    {
        //
    }

    public MessageTileEntityElectricItemBuffer(TileEntityElectricItemBuffer tileEntityElectricItemBuffer)
    {
        this.orientation = (byte) tileEntityElectricItemBuffer.getOrientation().ordinal();
    }


    // Buffer Stuff
    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        orientation = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
    }

    @Override
    public IMessage onMessage(MessageTileEntityElectricItemBuffer message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
        if (tileEntity instanceof TileEntityElectricItemBuffer)
        {
            ((TileEntityElectricItemBuffer) tileEntity).setOrientation(message.orientation);
        }
        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityElectricItemBuffer - x:%s, y:%s, z:%s, orientation:%s",
                x, y, z, orientation);
    }
}
