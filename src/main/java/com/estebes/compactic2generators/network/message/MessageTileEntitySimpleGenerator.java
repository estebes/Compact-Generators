package com.estebes.compactic2generators.network.message;

import com.estebes.compactic2generators.tileentity.TileEntitySimpleGenerator;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntitySimpleGenerator implements IMessage, IMessageHandler<MessageTileEntitySimpleGenerator, IMessage>
{
    public int x, y, z;
    public byte orientation;
    public boolean isWorking;

    public MessageTileEntitySimpleGenerator()
    {
    }

    public MessageTileEntitySimpleGenerator(TileEntitySimpleGenerator tileEntitySimpleGenerator)
    {
        this.x = tileEntitySimpleGenerator.xCoord;
        this.y = tileEntitySimpleGenerator.yCoord;
        this.z = tileEntitySimpleGenerator.zCoord;
        this.orientation = (byte) tileEntitySimpleGenerator.getOrientation().ordinal();
        this.isWorking = tileEntitySimpleGenerator.getWorkingState();
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orientation = buf.readByte();
        this.isWorking = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeBoolean(isWorking);
    }

    @Override
    public IMessage onMessage(MessageTileEntitySimpleGenerator message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
        if (tileEntity instanceof TileEntitySimpleGenerator)
        {
            ((TileEntitySimpleGenerator) tileEntity).setOrientation(message.orientation);
            ((TileEntitySimpleGenerator) tileEntity).setWorkingState(message.isWorking);
        }
        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntitySimpleGenerator - x:%s, y:%s, z:%s, orientation:%s, isWorking:%s", x, y, z, orientation, isWorking);
    }
}

