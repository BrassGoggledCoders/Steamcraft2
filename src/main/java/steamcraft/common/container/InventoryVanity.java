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

	/** In case your inventory name is too generic, define a name to store the NBT tag in as well */
	private final String tagName = "tagVanityInventory";

	/** Define the inventory size here for easy reference */
	public static final int INV_SIZE = 6;

	/** Inventory's size must be same as number of slots you add to the Container class */
	ItemStack[] inventory = new ItemStack[INV_SIZE];

	public InventoryVanity() {}

	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		ItemStack is = this.getStackInSlot(slot);
		
		if (is != null)
		{
			if (is.stackSize > amount)
			{
				is = is.splitStack(amount);
				
				if (is.stackSize == 0)
				{
					this.setInventorySlotContents(slot, null);
				}
			}
			else
			{
				this.setInventorySlotContents(slot, null);
			}

			this.markDirty();
		}
		
		return is;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack is = this.getStackInSlot(slot);
		
		if (is != null)
		{
			this.setInventorySlotContents(slot, null);
		}
		
		return is;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack is)
	{
		this.inventory[slot] = is;

		if (is != null && is.stackSize > this.getInventoryStackLimit())
		{
			is.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
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
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
	}

	/**
	 * This method doesn't seem to do what it claims to do, as
	 * items can still be left-clicked and placed in the inventory
	 * even when this returns false
	 */
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
		return itemstack.getItem() instanceof Item;
	}

	public void writeToNBT(NBTTagCompound tagCompound)
	{
		NBTTagList tagList = new NBTTagList();

		for (int i = 0; i < this.getSizeInventory(); ++i)
		{
			if (this.getStackInSlot(i) != null)
			{
				NBTTagCompound newTagCompound = new NBTTagCompound();
				newTagCompound.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(newTagCompound);
				tagList.appendTag(newTagCompound);
			}
		}

		// We're storing our items in a custom tag list using our 'tagName' from above
		// to prevent potential conflicts
		tagCompound.setTag(this.tagName, tagList);
	}

	public void readFromNBT(NBTTagCompound tagCompound)
	{
		NBTTagList tagList = (NBTTagList)tagCompound.getTag(this.tagName);

		for (int i = 0; i < tagList.tagCount(); ++i)
		{
			NBTTagCompound newTagCompound = (NBTTagCompound)tagList.getCompoundTagAt(i);
			byte slot = newTagCompound.getByte("Slot");

			if (slot >= 0 && slot < this.getSizeInventory())
			{
				this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(newTagCompound));
			}
		}
	}

	@Override
	public void closeInventory() {}

	@Override
	public String getInventoryName() 
	{
		return this.getInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return true;
	}

	@Override
	public void markDirty() 
	{
		for (int slot = 0; slot < this.getSizeInventory(); ++slot)
		{
			if (this.getStackInSlot(slot) != null && this.getStackInSlot(slot).stackSize == 0)
				this.setInventorySlotContents(slot, null);
		}
	}

	@Override
	public void openInventory() 
	{
	}
}