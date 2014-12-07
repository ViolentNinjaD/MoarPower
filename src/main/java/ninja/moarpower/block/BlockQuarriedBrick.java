package ninja.moarpower.block;

import net.minecraft.block.material.Material;
import ninja.moarpower.MoarPower;
import ninja.moarpower.init.MPBlocks;

/** @author ViolentNinjaD
    LGPLv3
**/
public class BlockQuarriedBrick extends BlockMP
{
    public BlockQuarriedBrick(int id)
    {
        super(id, Material.rock);
        this.setUnlocalizedName(MPBlocks.quarriedBrickName);
        this.setCreativeTab(MoarPower.tabMP);
    }
}
