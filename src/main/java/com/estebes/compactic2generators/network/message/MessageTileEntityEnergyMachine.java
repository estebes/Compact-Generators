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
    private int storedEnergy;
    private boolean enetChecker;


    // Constructor
    public MessageTileEntityEnergyMachine()
    {
    }

    public MessageTileEntityEnergyMachine(TileEntityEnergyMachine tileEntityEnergyMachine)
    {
        this.storedEnergy = tileEntityEnergyMachine.getStoredEnergy();
    }


    // Buffer Stuff
    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        storedEnergy = buf.readInt();
        enetChecker = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(storedEnergy);
        buf.writeBoolean(enetChecker);
    }

    @Override
    public IMessage onMessage(MessageTileEntityEnergyMachine message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
        if (tileEntity instanceof TileEntityEnergyMachine)
        {
            ((TileEntityEnergyMachine) tileEntity).setStoredEnergy(message.storedEnergy);
        }
        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityEnergyMachine - x:%s, y:%s, z:%s, storedEnergy:%s", x, y, z, storedEnergy);
    }
}
