package com.estebes.compactic2generators.network.message;

import com.estebes.compactic2generators.tileentity.TileEntityEnergyMachine;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityEnergyMachine implements IMessage, IMessageHandler<MessageTileEntityEnergyMachine, IMessage>
{
    // Variables
    private int x, y, z;
    private byte orientation;


    // Constructor
    public MessageTileEntityEnergyMachine()
    {
    }

    public MessageTileEntityEnergyMachine(TileEntityEnergyMachine tileEntityEnergyMachine)
    {
        this.orientation = (byte) tileEntityEnergyMachine.getOrientation().ordinal();
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
    public IMessage onMessage(MessageTileEntityEnergyMachine message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
        if (tileEntity instanceof TileEntityEnergyMachine)
        {
            ((TileEntityEnergyMachine) tileEntity).setOrientation(message.orientation);
        }
        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityEnergyMachine - x:%s, y:%s, z:%s, orientation:%s", x, y, z, orientation);
    }
}
