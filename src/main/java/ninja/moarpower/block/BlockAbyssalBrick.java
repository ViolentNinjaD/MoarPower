package ninja.moarpower.block;

import net.minecraft.block.material.Material;
import ninja.moarpower.MoarPower;
import ninja.moarpower.init.MPBlocks;

/** @author ViolentNinjaD
    LGPLv3
**/
public class BlockAbyssalBrick extends BlockMP
{
    public BlockAbyssalBrick(int id)
    {
        super(id, Material.rock);
        this.setUnlocalizedName(MPBlocks.abyssalBrickName);
        this.setCreativeTab(MoarPower.tabMP);
    }
}
