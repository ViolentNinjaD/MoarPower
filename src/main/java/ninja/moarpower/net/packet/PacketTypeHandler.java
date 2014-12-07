package ninja.moarpower.net.packet;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import ninja.moarpower.MoarPower;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * Created by David on 21/11/2014.
 */
public enum PacketTypeHandler
{
    TILE(PacketTileUpdate.class),
    TILE_WITH_ITEM(PacketTileWithItemUpdate.class);

    private Class<? extends PacketMP> clazz;
    PacketTypeHandler(Class<? extends PacketMP> clazz)
    {
        this.clazz = clazz;
    }
    public static PacketMP buildPacket(byte[] data)
    {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        int selector = bis.read();
        DataInputStream dis = new DataInputStream(bis);
        PacketMP packet = null;
        try
        {
            packet = values()[selector].clazz.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
        packet.readPopulate(dis);
        return packet;
    }
    public static PacketMP buildPacket(PacketTypeHandler type)
    {
        PacketMP packet = null;
        try
        {
            packet = values()[type.ordinal()].clazz.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
        return packet;
    }
    public static Packet populatePacket(PacketMP packetEE)
    {
        byte[] data = packetEE.populate();
        Packet250CustomPayload packet250 = new Packet250CustomPayload();
        packet250.channel = MoarPower.modid;
        packet250.data = data;
        packet250.length = data.length;
        packet250.isChunkDataPacket = packetEE.isChunkDataPacket;
        return packet250;
    }
}
