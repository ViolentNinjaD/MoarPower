package ninja.moarpower.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import ninja.moarpower.NBT;
import ninja.moarpower.net.packet.PacketTileUpdate;
import ninja.moarpower.net.packet.PacketTypeHandler;

/**
 * Created by David on 21/11/2014.
 */
public class TileMP extends TileEntity
{
    protected ForgeDirection orientation;
    protected byte state;
    protected String customName;
    public TileMP()
    {
        orientation = ForgeDirection.SOUTH;
        state = 0;
        customName = "";
    }
    public ForgeDirection getOrientation()
    {
        return orientation;
    }
    public void setOrientation(ForgeDirection orientation)
    {
        this.orientation = orientation;
    }
    public void setOrientation(int orientation)
    {
        this.orientation = ForgeDirection.getOrientation(orientation);
    }
    public short getState()
    {
        return state;
    }
    public void setState(byte state)
    {
        this.state = state;
    }
    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }
    public String getCustomName()
    {
        return customName;
    }
    public void setCustomName(String customName)
    {
        this.customName = customName;
    }
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        if (nbtTagCompound.hasKey(NBT.NBT_TE_DIRECTION_KEY))
        {
            orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte(NBT.NBT_TE_DIRECTION_KEY));
        }
        if (nbtTagCompound.hasKey(NBT.NBT_TE_STATE_KEY))
        {
            state = nbtTagCompound.getByte(NBT.NBT_TE_STATE_KEY);
        }
        if (nbtTagCompound.hasKey(NBT.NBT_CUSTOM_NAME))
        {
            customName = nbtTagCompound.getString(NBT.NBT_CUSTOM_NAME);
        }
    }
    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setByte(NBT.NBT_TE_DIRECTION_KEY, (byte) orientation.ordinal());
        nbtTagCompound.setByte(NBT.NBT_TE_STATE_KEY, state);
        if (this.hasCustomName())
        {
            nbtTagCompound.setString(NBT.NBT_CUSTOM_NAME, customName);
        }
    }
    @Override
    public Packet getDescriptionPacket()
    {
        return PacketTypeHandler.populatePacket(new PacketTileUpdate(xCoord, yCoord, zCoord, orientation, state, customName));
    }
    @Override
    public String toString()
    {
        return String.format("TileMP Data - Class: %s, xCoord: %d, yCoord: %d, zCoord: %d, customName: '%s', orientation: %s, state: %d\n", this.getClass().getSimpleName(), xCoord, yCoord, zCoord, customName, orientation, state);
    }
}
