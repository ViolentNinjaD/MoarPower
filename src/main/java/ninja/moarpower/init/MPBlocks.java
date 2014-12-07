package ninja.moarpower.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import ninja.moarpower.block.*;

/** @author ViolentNinjaD
    LGPLv3
**/
public class MPBlocks 
{
    /**
     * Stone Fancifier
     */
    public static final String stoneFancifierName = "stoneFancifier";
    public static int STONE_FANCIFIER_DEFAULTID = 3321;
    //TODO: Setup config stuff!
    public static int STONE_FANCIFIER_ID = 3321;
    public static final BlockMP stoneFancifier = new BlockStoneFancifier(STONE_FANCIFIER_ID);

    /**
     * Abyssal (Stone, Block and Brick)
     */
    public static int ABYSSAL_STONE_ID = 3322;
    public static int ABYSSAL_STONE_DEFAULTID = 3322;
    public static final String abyssalStoneName = "abyssalStone";
    public static final BlockMP abyssalStone = new BlockAbyssalStone(ABYSSAL_STONE_ID);

    public static int ABYSSAL_BLOCK_ID = 3323;
    public static int ABYSSAL_BLOCK_DEFAULTID = 3323;
    public static final String abyssalBlockName = "abyssalBlock";
    public static final BlockMP abyssalBlock = new BlockAbyssalBlock(ABYSSAL_BLOCK_ID);

    public static int ABYSSAL_BRICK_ID = 3324;
    public static int ABYSSAL_BRICK_DEFAULTID = 3324;
    public static final String abyssalBrickName = "abyssalBrick";
    public static final BlockMP abyssalBrick = new BlockAbyssalBrick(ABYSSAL_BRICK_ID);

    /**
     * Quarried (Stone, Block and Brick)
     */
    public static int QUARRIED_STONE_ID = 3325;
    public static int QUARRIED_STONE_DEFAULTID = 3325;
    public static final String quarriedStoneName = "quarriedStone";
    public static final BlockMP quarriedStone = new BlockQuarriedStone(QUARRIED_STONE_ID);

    public static int QUARRIED_BLOCK_ID = 3326;
    public static int QUARRIED_BLOCK_DEFAULTID = 3326;
    public static final String quarriedBlockName = "quarriedBlock";
    public static final BlockMP quarriedBlock = new BlockQuarriedBlock(QUARRIED_BLOCK_ID);

    public static int QUARRIED_BRICK_ID = 3327;
    public static int QUARRIED_BRICK_DEFAULTID = 3327;
    public static final String quarriedBrickName = "quarriedBrick";
    public static final BlockMP quarriedBrick = new BlockQuarriedBrick(QUARRIED_BRICK_ID);

    public static void init()
    {
        GameRegistry.registerBlock(stoneFancifier, stoneFancifierName);
        GameRegistry.registerBlock(abyssalStone, abyssalStoneName);
        GameRegistry.registerBlock(abyssalBlock, abyssalBlockName);
        GameRegistry.registerBlock(abyssalBrick, abyssalBrickName);
        GameRegistry.registerBlock(quarriedStone, quarriedStoneName);
        GameRegistry.registerBlock(quarriedBlock, quarriedBlockName);
        GameRegistry.registerBlock(quarriedBrick, quarriedBrickName);
    }
}
