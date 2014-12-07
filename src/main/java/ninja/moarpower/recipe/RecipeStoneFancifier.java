package ninja.moarpower.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ninja.moarpower.isteal.ee3.OreStack;
import ninja.moarpower.isteal.ee3.WrappedStack;

import java.util.ArrayList;
import java.util.List;

/** @author ViolentNinjaD
    LGPLv3
**/
public class RecipeStoneFancifier 
{
    private ItemStack recipeOutput;
    private WrappedStack stoneStack;
    private ItemStack dyeStack;
    public RecipeStoneFancifier(ItemStack recipeOutput, ItemStack stoneStack, ItemStack dyeStack)
    {
        this.recipeOutput = recipeOutput.copy();
        this.stoneStack = new WrappedStack(stoneStack);
        this.dyeStack = dyeStack.copy();
    }
    public RecipeStoneFancifier(ItemStack recipeOutput, OreStack stoneStack, ItemStack dyeStack)
    {
        this.recipeOutput = recipeOutput.copy();
        this.stoneStack = new WrappedStack(stoneStack);
        this.dyeStack = dyeStack.copy();
    }
    public boolean matches(RecipeStoneFancifier recipeStoneFancifier)
    {
        return compareItemStacks(this.recipeOutput, recipeStoneFancifier.recipeOutput) && matches(recipeStoneFancifier.stoneStack, recipeStoneFancifier.dyeStack);
    }
    public boolean matches(ItemStack stoneStack, ItemStack dyeStack)
    {
        if (OreDictionary.getOreID(stoneStack) != -1)
        {
            if (matches(new WrappedStack(new OreStack(stoneStack)), dyeStack))
            {
                return matches(new WrappedStack(new OreStack(stoneStack)), dyeStack);
            }
        }
        return matches(new WrappedStack(stoneStack), dyeStack);
    }
    public boolean matches(WrappedStack stoneStack, ItemStack dyeStack)
    {
        return compareStacks(this.stoneStack, stoneStack) && compareItemStacks(this.dyeStack, dyeStack);
    }
    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }
    public WrappedStack[] getRecipeInputs()
    {
        return new WrappedStack[]{stoneStack, new WrappedStack(dyeStack)};
    }
    public List<WrappedStack> getRecipeInputsAsWrappedStacks()
    {
        List<WrappedStack> recipeInputs = new ArrayList<WrappedStack>();
        recipeInputs.add(new WrappedStack(stoneStack));
        recipeInputs.add(new WrappedStack(dyeStack));
        return recipeInputs;
    }
    @Override
    public boolean equals(Object object)
    {
        if (object instanceof RecipeStoneFancifier)
        {
            return matches((RecipeStoneFancifier) object);
        }
        return false;
    }
    @Override
    public String toString()
    {
        return String.format("Output: %s, Input: %s, Dust: %s", recipeOutput, stoneStack, dyeStack);
    }
    private static boolean compareStacks(WrappedStack wrappedStack1, WrappedStack wrappedStack2)
    {
        if (wrappedStack1 != null && wrappedStack1.getWrappedStack() != null && wrappedStack2 != null && wrappedStack2.getWrappedStack() != null)
        {
            if (wrappedStack1.getWrappedStack() instanceof ItemStack && wrappedStack2.getWrappedStack() instanceof ItemStack)
            {
                ItemStack itemStack1 = (ItemStack) wrappedStack1.getWrappedStack();
                itemStack1.stackSize = wrappedStack1.getStackSize();
                ItemStack itemStack2 = (ItemStack) wrappedStack2.getWrappedStack();
                itemStack2.stackSize = wrappedStack2.getStackSize();
                return compareItemStacks(itemStack1, itemStack2);
            }
            else if (wrappedStack1.getWrappedStack() instanceof OreStack && wrappedStack2.getWrappedStack() instanceof OreStack)
            {
                if (((OreStack) wrappedStack1.getWrappedStack()).oreName.equalsIgnoreCase(((OreStack) wrappedStack2.getWrappedStack()).oreName))
                {
                    return wrappedStack2.getStackSize() >= wrappedStack1.getStackSize();
                }
            }
        }
        return false;
    }
    private static boolean compareItemStacks(ItemStack itemStack1, ItemStack itemStack2)
    {
        if (itemStack1 != null && itemStack2 != null)
        {
            if (itemStack1.itemID == itemStack2.itemID)
            {
                if (itemStack1.getItemDamage() == itemStack2.getItemDamage() || itemStack1.getItemDamage() == OreDictionary.WILDCARD_VALUE || itemStack2.getItemDamage() == OreDictionary.WILDCARD_VALUE)
                {
                    if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound())
                    {
                        if (itemStack1.getTagCompound().hashCode() == itemStack2.getTagCompound().hashCode())
                        {
                            return itemStack2.stackSize >= itemStack1.stackSize;
                        }
                    }
                    else if (!itemStack1.hasTagCompound() && !itemStack2.hasTagCompound())
                    {
                        return itemStack2.stackSize >= itemStack1.stackSize;
                    }
                }
            }
        }
        return false;
    }
}
