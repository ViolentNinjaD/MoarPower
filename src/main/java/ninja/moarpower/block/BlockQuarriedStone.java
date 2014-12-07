package ninja.moarpower.block;

import net.minecraft.block.material.Material;
import ninja.moarpower.MoarPower;
import ninja.moarpower.init.MPBlocks;

/** @author ViolentNinjaD
    LGPLv3
**/
public class BlockQuarriedStone extends BlockMP
{
    public BlockQuarriedStone(int id)
    {
        super(id, Material.rock);
        this.setUnlocalizedName(MPBlocks.quarriedStoneName);
        this.setCreativeTab(MoarPower.tabMP);
    }
}
