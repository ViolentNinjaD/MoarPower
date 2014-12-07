package ninja.moarpower.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ninja.moarpower.init.MPBlocks;

import java.util.ArrayList;
import java.util.List;

/** @author ViolentNinjaD
    LGPLv3
**/
public class RecipesStoneFancifier 
{
    private static RecipesStoneFancifier fancifierRegistry = null;
    private List<RecipeStoneFancifier> fancifierRecipes;
    private RecipesStoneFancifier()
    {
        fancifierRecipes = new ArrayList<RecipeStoneFancifier>();
    }
    
    public static RecipesStoneFancifier getInstance()
    {
        if (fancifierRegistry == null)
        {
            fancifierRegistry = new RecipesStoneFancifier();
            fancifierRegistry.init();
        }
        return fancifierRegistry;
    }
    private void init()
    {
        fancifierRegistry.addRecipe(new ItemStack(MPBlocks.abyssalStone.blockID, 1, 0), new ItemStack(Block.stone), new ItemStack(Item.dyePowder, 1, 0));
        fancifierRegistry.addRecipe(new ItemStack(MPBlocks.quarriedStone.blockID, 1, 0), new ItemStack(Block.stone), new ItemStack(Item.dyePowder, 1, 15));
    }
    
    public void addRecipe(ItemStack recipeOutput, ItemStack recipeInputStone, ItemStack recipeInputDye)
    {
        addRecipe(new RecipeStoneFancifier(recipeOutput, recipeInputStone, recipeInputDye));
    }

    public void addRecipe(RecipeStoneFancifier recipeFancifier)
    {
        if (!fancifierRecipes.contains(recipeFancifier))
        {
            fancifierRecipes.add(recipeFancifier);
        }
        else
        {
            System.out.println(String.format("[MoarPower-DEBUG-MESSAGE]Attempted to add RecipeStoneFancifier '%s' but already exists in the recipe list", recipeFancifier));
        }
    }
    public ItemStack getResult(ItemStack recipeInputStone, ItemStack recipeInputDye)
    {
        for (RecipeStoneFancifier recipeFancifier : fancifierRecipes)
        {
            if (recipeFancifier.matches(recipeInputStone, recipeInputDye))
            {
                return recipeFancifier.getRecipeOutput();
            }
        }
        return null;
    }
    public RecipeStoneFancifier getRecipe(ItemStack recipeInputStone, ItemStack recipeInputDye)
    {
        for (RecipeStoneFancifier recipeFancifier : fancifierRecipes)
        {
            if (recipeFancifier.matches(recipeInputStone, recipeInputDye))
            {
                return recipeFancifier;
            }
        }
        return null;
    }
    public List<RecipeStoneFancifier> getRecipes()
    {
        return fancifierRecipes;
    }
}
