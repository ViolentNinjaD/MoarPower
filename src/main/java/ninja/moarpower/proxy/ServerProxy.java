package ninja.moarpower.proxy;

import net.minecraftforge.common.ForgeDirection;

/**
 * Created by David on 21/11/2014.
 */
public class ServerProxy extends CommonProxy
{
    public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName)
    {

    }

    public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, int itemID, int metaData, int stackSize)
    {

    }
}
