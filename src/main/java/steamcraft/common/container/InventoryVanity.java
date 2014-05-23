/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Apr 8, 2014, 12:44:56 PM]
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
	/** The name for your custom inventory, possibly just "Inventory" */
	private final String name = "Vanity Items";

	/**
	 * In case your inventory name is too generic, define a name to store the
	 * NBT tag in as well
	 */
	private final String tagName = "tagVanityInventory";

	/** Define the inventory size here for easy reference */
	public static final int INV_SIZE = 6;

	/**
	 * Inventory's size must be same as number of slots you add to the Container
	 * class
	 */
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
				{
					setInventorySlotContents(slot, null);
				}
			}
			else
			{
				setInventorySlotContents(slot, null);
			}

			markDirty();
		}

		return is;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(final int slot)
	{
		final ItemStack is = getStackInSlot(slot);

		if (is != null)
		{
			setInventorySlotContents(slot, null);
		}

		return is;
	}

	@Override
	public void setInventorySlotContents(final int slot, final ItemStack is)
	{
		inventory[slot] = is;

		if (is != null && is.stackSize > getInventoryStackLimit())
		{
			is.stackSize = getInventoryStackLimit();
		}

		markDirty();
	}

	/**
	 * Our custom slots are similar to armor - only one item per slot
	 */
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

	/**
	 * This method doesn't seem to do what it claims to do, as items can still
	 * be left-clicked and placed in the inventory even when this returns false
	 */
	@Override
	public boolean isItemValidForSlot(final int slot, final ItemStack itemstack)
	{
		return itemstack.getItem() instanceof Item;
	}

	public void writeToNBT(final NBTTagCompound tagCompound)
	{
		final NBTTagList tagList = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != null)
			{
				final NBTTagCompound newTagCompound = new NBTTagCompound();
				newTagCompound.setByte("Slot", (byte) i);
				getStackInSlot(i).writeToNBT(newTagCompound);
				tagList.appendTag(newTagCompound);
			}
		}

		// We're storing our items in a custom tag list using our 'tagName' from
		// above
		// to prevent potential conflicts
		tagCompound.setTag(tagName, tagList);
	}

	public void readFromNBT(final NBTTagCompound tagCompound)
	{
		final NBTTagList tagList = (NBTTagList) tagCompound.getTag(tagName);

		for (int i = 0; i < tagList.tagCount(); ++i)
		{
			final NBTTagCompound newTagCompound = tagList.getCompoundTagAt(i);
			final byte slot = newTagCompound.getByte("Slot");

			if (slot >= 0 && slot < getSizeInventory())
			{
				setInventorySlotContents(slot,
						ItemStack.loadItemStackFromNBT(newTagCompound));
			}
		}
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public String getInventoryName()
	{
		return getInventoryName();
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
		{
			if (getStackInSlot(slot) != null
					&& getStackInSlot(slot).stackSize == 0)
			{
				setInventorySlotContents(slot, null);
			}
		}
	}

	@Override
	public void openInventory()
	{
	}
}