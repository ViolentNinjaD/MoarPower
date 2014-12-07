package ninja.moarpower.block;

import net.minecraft.block.material.Material;
import ninja.moarpower.CreativeTabMP;
import ninja.moarpower.MoarPower;
import ninja.moarpower.init.MPBlocks;

/** @author ViolentNinjaD
    LGPLv3
**/
public class BlockAbyssalStone extends BlockMP
{
    public BlockAbyssalStone(int id)
    {
        super(id, Material.rock);
        this.setUnlocalizedName(MPBlocks.abyssalStoneName);
        this.setCreativeTab(MoarPower.tabMP);
    }
}
