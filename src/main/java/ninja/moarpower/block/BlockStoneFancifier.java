package ninja.moarpower.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ninja.moarpower.GuiIds;
import ninja.moarpower.MoarPower;
import ninja.moarpower.init.MPBlocks;
import ninja.moarpower.tile.TileStoneFancifier;

/** @author ViolentNinjaD
    LGPLv3
**/
public class BlockStoneFancifier extends BlockMP implements ITileEntityProvider
{
    public BlockStoneFancifier(int id)
    {
        super(id, Material.rock);
        this.setUnlocalizedName(MPBlocks.stoneFancifierName);
        this.setHardness(5.0F);
        super.setCreativeTab(MoarPower.tabMP);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileStoneFancifier();
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return true;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return true;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if (world.getBlockTileEntity(x, y, z) instanceof TileStoneFancifier)
        {
            if (((TileStoneFancifier) world.getBlockTileEntity(x, y, z)).getState() == 1)
            {
                return 15;
            }
        }
        return super.getLightValue(world, x, y, z);
    }

    @Override
    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
        TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
        return tileentity != null ? tileentity.receiveClientEvent(par5, par6) : false;
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!world.isRemote)
            {
                if (world.getBlockTileEntity(x, y, z) instanceof TileStoneFancifier)
                {
                    player.openGui(MoarPower.instance, GuiIds.STONE_FANCIFIER, world, x, y, z);
                }
            }
            return true;
        }
    }
}
