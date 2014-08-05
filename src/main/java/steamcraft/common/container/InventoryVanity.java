/**
 * This class was created by BrassGoggledCoders modding team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 */
package steamcraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * @author warlordjones
 * 
 */
public class InventoryVanity implements IInventory
{
	private final String name = "Vanity Items";

	private final String tagName = "tagVanityInventory";

	public static final int INV_SIZE = 6;

	ItemStack[] inventory = new ItemStack[INV_SIZE];

	public InventoryVanity()
	{
	}

	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(final int slot)
	{
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(final int slot, final int amount)
	{
		ItemStack is = getStackInSlot(slot);

		if (is != null)
		{
			if (is.stackSize > amount)
			{
				is = is.splitStack(amount);

				if (is.stackSize == 0)
					setInventorySlotContents(slot, null);
			}
			else
				setInventorySlotContents(slot, null);

			markDirty();
		}

		return is;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(final int slot)
	{
		final ItemStack is = getStackInSlot(slot);

		if (is != null)
			setInventorySlotContents(slot, null);

		return is;
	}

	@Override
	public void setInventorySlotContents(final int slot, final ItemStack is)
	{
		inventory[slot] = is;

		if ((is != null) && (is.stackSize > getInventoryStackLimit()))
			is.stackSize = getInventoryStackLimit();

		markDirty();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(final EntityPlayer player)
	{
		return true;
	}

	@Override
	public boolean isItemValidForSlot(final int slot, final ItemStack itemstack)
	{
		return itemstack.getItem() instanceof Item;
	}

	public void writeToNBT(final NBTTagCompound tagCompound)
	{
		final NBTTagList tagList = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); ++i)
			if (getStackInSlot(i) != null)
			{
				final NBTTagCompound newTagCompound = new NBTTagCompound();
				newTagCompound.setByte("Slot", (byte) i);
				getStackInSlot(i).writeToNBT(newTagCompound);
				tagList.appendTag(newTagCompound);
			}

		// We're storing our items in a custom tag list using our 'tagName' from
		// above
		// to prevent potential conflicts
		tagCompound.setTag(tagName, tagList);
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		// now you must include the NBTBase type ID when getting the list;
		// NBTTagCompound's ID is 10
		NBTTagList items = compound.getTagList(tagName, compound.getId());
		for (int i = 0; i < items.tagCount(); ++i)
		{
			// tagAt(int) has changed to getCompoundTagAt(int)
			NBTTagCompound item = items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			if ((slot >= 0) && (slot < getSizeInventory()))
				inventory[slot] = ItemStack.loadItemStackFromNBT(item);
		}
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public String getInventoryName()
	{
		return name;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return true;
	}

	@Override
	public void markDirty()
	{
		for (int slot = 0; slot < getSizeInventory(); ++slot)
			if ((getStackInSlot(slot) != null) && (getStackInSlot(slot).stackSize == 0))
				setInventorySlotContents(slot, null);
	}

	@Override
	public void openInventory()
	{
	}
}