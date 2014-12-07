package ninja.moarpower.block;

import net.minecraft.block.material.Material;
import ninja.moarpower.MoarPower;
import ninja.moarpower.init.MPBlocks;

/** @author ViolentNinjaD
    LGPLv3
**/
public class BlockAbyssalBlock extends BlockMP
{
    public BlockAbyssalBlock(int id)
    {
        super(id, Material.rock);
        this.setUnlocalizedName(MPBlocks.abyssalBlockName);
        this.setCreativeTab(MoarPower.tabMP);
    }
}
