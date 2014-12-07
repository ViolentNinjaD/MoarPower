package ninja.moarpower.proxy;

import cpw.mods.fml.common.registry.GameRegistry;
import ninja.moarpower.init.MPBlocks;
import ninja.moarpower.tile.TileStoneFancifier;

/**
 * Created by David on 21/11/2014.
 */
public abstract class CommonProxy implements IProxy
{
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileStoneFancifier.class, "tile." + MPBlocks.stoneFancifierName);
    }
}
