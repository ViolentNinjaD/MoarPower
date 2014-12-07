package ninja.moarpower.tile;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import ninja.moarpower.Reference;
import ninja.moarpower.net.packet.PacketTileWithItemUpdate;
import ninja.moarpower.net.packet.PacketTypeHandler;
import ninja.moarpower.recipe.RecipeStoneFancifier;
import ninja.moarpower.recipe.RecipesStoneFancifier;

/** @author ViolentNinjaD
    LGPLv3
**/
public class TileStoneFancifier extends TileMP implements ISidedInventory
{
    private ItemStack[] inventory;
    public static final int INVENTORY_SIZE = 3;

    public static final int STONE_INVENTORY_INDEX = 0;
    public static final int DYE_INVENTORY_INDEX = 1;
    public static final int OUTPUT_INVENTORY_INDEX = 2;

    public ItemStack outputItemStack;

    public TileStoneFancifier()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= decrementAmount)
            {
                setInventorySlotContents(slotIndex, null);
            }
            else
            {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }
        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName()
    {
        return this.hasCustomName() ? this.getCustomName() : Reference.CONTAINER_STONEFANCIFIER_NAME;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }


    @Override
    public boolean receiveClientEvent(int eventId, int eventData)
    {
        if (eventId == 1)
        {
            this.state = (byte) eventData;
            this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);
            return true;
        }
        else
        {
            return super.receiveClientEvent(eventId, eventData);
        }
    }

    @Override
    public void openChest()
    {
    }

    @Override
    public void closeChest()
    {
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        NBTTagList tagList = nbtTagCompound.getTagList("Items");
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            if (inventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag("Items", tagList);
    }

    @Override
    public boolean isInvNameLocalized()
    {
        return this.hasCustomName();
    }

    public static boolean isStone(ItemStack checkStack)
    {
        if (checkStack == new ItemStack(Block.stone))
        {
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        switch (slotIndex)
        {
            case STONE_INVENTORY_INDEX:
            {
                return isStone(itemStack);
            }
            case DYE_INVENTORY_INDEX:
            {
                return true;
            }
            default:
            {
                return false;
            }
        }
    }

    @Override
    public Packet getDescriptionPacket()
    {
        ItemStack itemStack = this.inventory[OUTPUT_INVENTORY_INDEX];
        if (itemStack != null && itemStack.stackSize > 0)
        {
            return PacketTypeHandler.populatePacket(new PacketTileWithItemUpdate(xCoord, yCoord, zCoord, orientation, state, customName, itemStack.itemID, itemStack.getItemDamage(), itemStack.stackSize));
        }
        else
        {
            return PacketTypeHandler.populatePacket(new PacketTileWithItemUpdate(xCoord, yCoord, zCoord, orientation, state, customName, -1, 0, 0));
        }
    }

    @Override
    public void onInventoryChanged()
    {
        PacketDispatcher.sendPacketToAllAround(this.xCoord, this.yCoord, this.zCoord, 128D, this.worldObj.provider.dimensionId, getDescriptionPacket());
        worldObj.updateAllLightTypes(xCoord, yCoord, zCoord);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("TileStoneFancifier Data - ");
        for (int i = 0; i < inventory.length; i++)
        {
            if (i != 0)
            {
                stringBuilder.append(", ");
            }
            if (inventory[i] != null)
            {
                stringBuilder.append(String.format("inventory[%d]: %s", i, inventory[i].toString()));
            }
            else
            {
                stringBuilder.append(String.format("inventory[%d]: empty", i));
            }
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return side == ForgeDirection.DOWN.ordinal() ? new int[]{OUTPUT_INVENTORY_INDEX} : new int[]{DYE_INVENTORY_INDEX, STONE_INVENTORY_INDEX, OUTPUT_INVENTORY_INDEX};
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side)
    {
        if (worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileStoneFancifier)
        {
            return isItemValidForSlot(slotIndex, itemStack);
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side)
    {
        return slotIndex == OUTPUT_INVENTORY_INDEX;
    }

    @Override
    public void updateEntity()
    {
        boolean sendUpdate = false;

        if (!this.worldObj.isRemote)
        {
            if (this.canFancify())
            {
                this.infuseItem();
                sendUpdate = true;
            }
        }

        if (sendUpdate)
        {
            this.onInventoryChanged();
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, this.state);
            PacketDispatcher.sendPacketToAllAround(this.xCoord, this.yCoord, this.zCoord, 128d, this.worldObj.provider.dimensionId, getDescriptionPacket());
            this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID);
        }
    }

    private boolean canFancify()
    {
        if (inventory[STONE_INVENTORY_INDEX] == null || inventory[DYE_INVENTORY_INDEX] == null)
        {
            return false;
        }
        else
        {
            ItemStack infusedItemStack = RecipesStoneFancifier.getInstance().getResult(inventory[STONE_INVENTORY_INDEX], inventory[DYE_INVENTORY_INDEX]);
            if (infusedItemStack == null)
            {
                return false;
            }
            if (inventory[OUTPUT_INVENTORY_INDEX] == null)
            {
                return true;
            }
            else
            {
                boolean outputEquals = this.inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(infusedItemStack);
                int mergedOutputStackSize = this.inventory[OUTPUT_INVENTORY_INDEX].stackSize + infusedItemStack.stackSize;
                if (outputEquals)
                {
                    return mergedOutputStackSize <= getInventoryStackLimit() && mergedOutputStackSize <= infusedItemStack.getMaxStackSize();
                }
            }
        }
        return false;
    }

    public void infuseItem()
    {
        if (this.canFancify())
        {
            RecipeStoneFancifier recipe = RecipesStoneFancifier.getInstance().getRecipe(inventory[STONE_INVENTORY_INDEX], inventory[DYE_INVENTORY_INDEX]);
            if (this.inventory[OUTPUT_INVENTORY_INDEX] == null)
            {
                this.inventory[OUTPUT_INVENTORY_INDEX] = recipe.getRecipeOutput().copy();
            }
            else if (this.inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(recipe.getRecipeOutput()))
            {
                inventory[OUTPUT_INVENTORY_INDEX].stackSize += recipe.getRecipeOutput().stackSize;
            }
            decrStackSize(STONE_INVENTORY_INDEX, recipe.getRecipeInputs()[0].getStackSize());
            decrStackSize(DYE_INVENTORY_INDEX, recipe.getRecipeInputs()[1].getStackSize());
        }
    }


}
