package com.estebes.compactic2generators.network.message;

import com.estebes.compactic2generators.tileentity.TileEntityBaseMachine;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityBaseMachine implements IMessage, IMessageHandler<MessageTileEntityBaseMachine, IMessage>
{
    // Variables
    private int x, y, z;
    private byte orientation;


    // Constructor
    public MessageTileEntityBaseMachine()
    {
    }

    public MessageTileEntityBaseMachine(TileEntityBaseMachine tileEntityBaseMachine)
    {
        this.orientation = (byte) tileEntityBaseMachine.getOrientation().ordinal();
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
    public IMessage onMessage(MessageTileEntityBaseMachine message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
        if (tileEntity instanceof TileEntityBaseMachine)
        {
            ((TileEntityBaseMachine) tileEntity).setOrientation(message.orientation);
        }
        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityBaseMachine - x:%s, y:%s, z:%s, orientation:%s", x, y, z, orientation);
    }
}
