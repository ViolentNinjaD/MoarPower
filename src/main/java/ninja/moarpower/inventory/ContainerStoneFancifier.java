package ninja.moarpower.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ninja.moarpower.tile.TileStoneFancifier;

/** @author ViolentNinjaD
    LGPLv3
**/
public class ContainerStoneFancifier extends Container
{
    private TileStoneFancifier fancifier;
    public ContainerStoneFancifier(InventoryPlayer inventoryPlayer, TileStoneFancifier fancifier)
    {
        this.fancifier = fancifier;

        this.addSlotToContainer(new Slot(fancifier, TileStoneFancifier.DYE_INVENTORY_INDEX, 56, 25));

        this.addSlotToContainer(new Slot(fancifier, TileStoneFancifier.STONE_INVENTORY_INDEX, 32, 25));

        this.addSlotToContainer(new SlotStoneFancifier(fancifier, TileStoneFancifier.OUTPUT_INVENTORY_INDEX, 136, 35));

        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 94 + inventoryRowIndex * 18));
            }
        }

        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 152));
        }
    }
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }
    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack())
        {
            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();
            if (slotIndex < TileStoneFancifier.INVENTORY_SIZE)
            {
                if (!this.mergeItemStack(slotItemStack, TileStoneFancifier.INVENTORY_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else
            {
                if (TileStoneFancifier.isStone(slotItemStack))
                {
                    if (!this.mergeItemStack(slotItemStack, TileStoneFancifier.STONE_INVENTORY_INDEX, TileStoneFancifier.OUTPUT_INVENTORY_INDEX, false))
                    {
                        return null;
                    }
                }

                else if (!this.mergeItemStack(slotItemStack, TileStoneFancifier.DYE_INVENTORY_INDEX, TileStoneFancifier.OUTPUT_INVENTORY_INDEX, false))
                {
                    return null;
                }
            }
            if (slotItemStack.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }

}
