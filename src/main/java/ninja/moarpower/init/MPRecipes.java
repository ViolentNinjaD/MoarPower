package ninja.moarpower.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

/** @author ViolentNinjaD
    LGPLv3
**/
public class MPRecipes 
{
    public static void init()
    {
        GameRegistry.addRecipe(new ItemStack(MPBlocks.abyssalBlock, 1, 0), "x", Character.valueOf('x'), new ItemStack(MPBlocks.abyssalStone, 1, 0));
        GameRegistry.addRecipe(new ItemStack(MPBlocks.abyssalBrick, 1, 0), "x", Character.valueOf('x'), new ItemStack(MPBlocks.abyssalBlock, 1, 0));
        GameRegistry.addRecipe(new ItemStack(MPBlocks.quarriedBlock, 1, 0), "x", Character.valueOf('x'), new ItemStack(MPBlocks.quarriedStone, 1, 0));
        GameRegistry.addRecipe(new ItemStack(MPBlocks.quarriedBrick, 1, 0), "x", Character.valueOf('x'), new ItemStack(MPBlocks.quarriedBlock, 1, 0));
    }
}
