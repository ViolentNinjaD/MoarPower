package ninja.moarpower.inventory;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ninja.moarpower.tile.TileStoneFancifier;

/**
 * @author ViolentNinjaD
 *         LGPLv3
 */
public class SlotStoneFancifier extends Slot
{
    public SlotStoneFancifier(IInventory inventory, int x, int y, int z)
    {
        super(inventory, x, y, z);
    }
    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }
    @Override
    public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        super.onPickupFromSlot(entityPlayer, itemStack);
        GameRegistry.onItemCrafted(entityPlayer, itemStack, inventory);
    }
}
