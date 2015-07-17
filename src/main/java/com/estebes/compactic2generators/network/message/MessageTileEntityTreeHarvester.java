package com.estebes.compactic2generators.network.message;

import com.estebes.compactic2generators.tileentity.machine.TileEntityTreeHarvester;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityTreeHarvester implements IMessage, IMessageHandler<MessageTileEntityTreeHarvester, IMessage>
{
    public int x, y, z;
    public int scanX, scanY, scanZ;
    public byte orientation;
    public int storedEnergy;
    public int tickCounter;

    public MessageTileEntityTreeHarvester()
    {
    }

    public MessageTileEntityTreeHarvester(TileEntityTreeHarvester tileEntityTreeHarvester)
    {
        this.x = tileEntityTreeHarvester.xCoord;
        this.y = tileEntityTreeHarvester.yCoord;
        this.z = tileEntityTreeHarvester.zCoord;
        this.orientation = (byte) tileEntityTreeHarvester.getOrientation().ordinal();
        this.storedEnergy = tileEntityTreeHarvester.getStoreEnergy();
        this.tickCounter = tileEntityTreeHarvester.getTickCounter();
        this.scanX = tileEntityTreeHarvester.getScanCoords()[0];
        this.scanY = tileEntityTreeHarvester.getScanCoords()[1];
        this.scanZ = tileEntityTreeHarvester.getScanCoords()[2];
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orientation = buf.readByte();
        this.storedEnergy = buf.readInt();
        this.tickCounter = buf.readInt();
        this.scanX = buf.readInt();
        this.scanY = buf.readInt();
        this.scanZ = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeInt(storedEnergy);
        buf.writeInt(tickCounter);
        buf.writeInt(scanX);
        buf.writeInt(scanY);
        buf.writeInt(scanZ);
    }

    @Override
    public IMessage onMessage(MessageTileEntityTreeHarvester message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
        if (tileEntity instanceof TileEntityTreeHarvester)
        {
            ((TileEntityTreeHarvester) tileEntity).setOrientation(message.orientation);
            ((TileEntityTreeHarvester) tileEntity).setStoredEnergy(message.storedEnergy);
            ((TileEntityTreeHarvester) tileEntity).setTickCounter(message.tickCounter);
            ((TileEntityTreeHarvester) tileEntity).setScanCoords(new int[]{message.scanX, message.scanY, message.scanZ});;
        }
        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityTreeHarvester - x:%s, y:%s, z:%s, orientation:%s, storedEnergy:%s, tickCounter:%s, scanX:%s, scanY:%s, scanZ:%s",
                x, y, z, orientation, storedEnergy, tickCounter, scanX, scanY, scanZ);
    }
}
