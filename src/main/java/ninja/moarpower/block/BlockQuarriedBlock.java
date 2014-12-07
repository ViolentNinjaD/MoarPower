package ninja.moarpower.block;

import net.minecraft.block.material.Material;
import ninja.moarpower.MoarPower;
import ninja.moarpower.init.MPBlocks;

/**
 * @author ViolentNinjaD
 *         LGPLv3
 */
public class BlockQuarriedBlock extends BlockMP
{
    public BlockQuarriedBlock(int id)
    {
        super(id, Material.rock);
        this.setUnlocalizedName(MPBlocks.quarriedBlockName);
        this.setCreativeTab(MoarPower.tabMP);
    }
}
