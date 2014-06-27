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

// TODO: Auto-generated Javadoc
/**
 * The Class InventoryVanity.
 * 
 * @author warlordjones
 */
public class InventoryVanity implements IInventory
{

	/** The name for your custom inventory, possibly just "Inventory". */
	@SuppressWarnings("unused")
	private final String name = "Vanity Items";

	/**
	 * In case your inventory name is too generic, define a name to store the
	 * NBT tag in as well.
	 */
	private final String tagName = "tagVanityInventory";

	/** Define the inventory size here for easy reference. */
	public static final int INV_SIZE = 6;

	/**
	 * Inventory's size must be same as number of slots you add to the Container
	 * class.
	 */
	ItemStack[] inventory = new ItemStack[INV_SIZE];

	/**
	 * Instantiates a new inventory vanity.
	 */
	public InventoryVanity()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.inventory.IInventory#getSizeInventory()
	 */
	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.inventory.IInventory#getStackInSlot(int)
	 */
	@Override
	public ItemStack getStackInSlot(final int slot)
	{
		return inventory[slot];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.inventory.IInventory#decrStackSize(int, int)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.inventory.IInventory#getStackInSlotOnClosing(int)
	 */
	@Override
	public ItemStack getStackInSlotOnClosing(final int slot)
	{
		final ItemStack is = getStackInSlot(slot);

		if (is != null)
			setInventorySlotContents(slot, null);

		return is;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.inventory.IInventory#setInventorySlotContents(int,
	 * net.minecraft.item.ItemStack)
	 */
	@Override
	public void setInventorySlotContents(final int slot, final ItemStack is)
	{
		inventory[slot] = is;

		if ((is != null) && (is.stackSize > getInventoryStackLimit()))
			is.stackSize = getInventoryStackLimit();

		markDirty();
	}

	/**
	 * Our custom slots are similar to armor - only one item per slot.
	 * 
	 * @return the inventory stack limit
	 */
	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.inventory.IInventory#isUseableByPlayer(net.minecraft.entity
	 * .player.EntityPlayer)
	 */
	@Override
	public boolean isUseableByPlayer(final EntityPlayer player)
	{
		return true;
	}

	/**
	 * This method doesn't seem to do what it claims to do, as items can still
	 * be left-clicked and placed in the inventory even when this returns false.
	 * 
	 * @param slot
	 *            the slot
	 * @param itemstack
	 *            the itemstack
	 * @return true, if is item valid for slot
	 */
	@Override
	public boolean isItemValidForSlot(final int slot, final ItemStack itemstack)
	{
		return itemstack.getItem() instanceof Item;
	}

	/**
	 * Write to nbt.
	 * 
	 * @param tagCompound
	 *            the tag compound
	 */
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

	/**
	 * Read from nbt.
	 * 
	 * @param compound
	 *            the compound
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.inventory.IInventory#closeInventory()
	 */
	@Override
	public void closeInventory()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.inventory.IInventory#getInventoryName()
	 */
	@Override
	public String getInventoryName()
	{
		return getInventoryName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.inventory.IInventory#hasCustomInventoryName()
	 */
	@Override
	public boolean hasCustomInventoryName()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.inventory.IInventory#markDirty()
	 */
	@Override
	public void markDirty()
	{
		for (int slot = 0; slot < getSizeInventory(); ++slot)
			if ((getStackInSlot(slot) != null) && (getStackInSlot(slot).stackSize == 0))
				setInventorySlotContents(slot, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.inventory.IInventory#openInventory()
	 */
	@Override
	public void openInventory()
	{
	}
}