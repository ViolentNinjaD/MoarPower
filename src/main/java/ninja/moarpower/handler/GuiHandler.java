package ninja.moarpower.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ninja.moarpower.GuiIds;
import ninja.moarpower.client.gui.inventory.GuiStoneFancifier;
import ninja.moarpower.inventory.ContainerStoneFancifier;
import ninja.moarpower.tile.TileStoneFancifier;

/** @author ViolentNinjaD
    LGPLv3
**/
public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GuiIds.STONE_FANCIFIER)
        {
            TileStoneFancifier tileStoneFancifier = (TileStoneFancifier) world.getBlockTileEntity(x, y, z);
            return new ContainerStoneFancifier(player.inventory, tileStoneFancifier);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GuiIds.STONE_FANCIFIER)
        {
            TileStoneFancifier tileStoneFancifier = (TileStoneFancifier) world.getBlockTileEntity(x, y, z);
            return new GuiStoneFancifier(player.inventory, tileStoneFancifier);
        }
        return null;
    }
}
