package ninja.moarpower.proxy;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ninja.moarpower.tile.TileMP;

/**
 * Created by David on 21/11/2014.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getBlockTileEntity(x, y, z);
        if (tileEntity != null)
        {
            if (tileEntity instanceof TileMP)
            {
                ((TileMP) tileEntity).setOrientation(orientation);
                ((TileMP) tileEntity).setState(state);
                ((TileMP) tileEntity).setCustomName(customName);
            }
        }
    }

    @Override
    public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, int itemID, int metaData, int stackSize)
    {
        World world = FMLClientHandler.instance().getClient().theWorld;
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        this.handleTileEntityPacket(x, y, z, orientation, state, customName);
    }
}
