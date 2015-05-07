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
package steamcraft.common.items.pda;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import net.minecraftforge.common.util.Constants;

public class InventoryPDA implements IInventory
{
	public final String name = "PDA Inventory";

	public static final int INV_SIZE = 8;

	private final ItemStack[] inventory = new ItemStack[INV_SIZE];

	private final ItemStack invItem;

	public InventoryPDA(ItemStack stack)
	{
		this.invItem = stack;
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		this.readFromNBT(stack.getTagCompound());
	}

	@Override
	public int getSizeInventory()
	{
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		ItemStack stack = this.getStackInSlot(slot);
		if(stack != null)
		{
			if(stack.stackSize > amount)
			{
				stack = stack.splitStack(amount);
				this.markDirty();
			}
			else
			{
				this.setInventorySlotContents(slot, null);
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = this.getStackInSlot(slot);
		if(stack != null)
		{
			this.setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
	{
		this.inventory[slot] = itemstack;

		if((itemstack != null) && (itemstack.stackSize > this.getInventoryStackLimit()))
		{
			itemstack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	@Override
	public String getInventoryName()
	{
		return this.name;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return this.name.length() > 0;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void markDirty()
	{
		for(int i = 0; i < this.getSizeInventory(); ++i)
		{
			if((this.getStackInSlot(i) != null) && (this.getStackInSlot(i).stackSize == 0))
				this.inventory[i] = null;
		}
		this.writeToNBT(this.invItem.getTagCompound());
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return player.getHeldItem() == this.invItem;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	/**
	 * This method doesn't seem to do what it claims to do, as items can still be left-clicked and placed in the inventory even when this returns false
	 */
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
		return itemstack.getItem() instanceof ItemChip;
	}

	/**
	 * A custom method to read our inventory from an ItemStack's NBT compound
	 */
	public void readFromNBT(NBTTagCompound tagcompound)
	{
		NBTTagList items = tagcompound.getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);

		for(int i = 0; i < items.tagCount(); ++i)
		{
			NBTTagCompound item = items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");

			if((slot >= 0) && (slot < this.getSizeInventory()))
			{
				this.inventory[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}
	}

	/**
	 * A custom method to write our inventory to an ItemStack's NBT compound
	 */
	public void writeToNBT(NBTTagCompound tagcompound)
	{
		// Create a new NBT Tag List to store itemstacks as NBT Tags
		NBTTagList items = new NBTTagList();

		for(int i = 0; i < this.getSizeInventory(); ++i)
		{
			// Only write stacks that contain items
			if(this.getStackInSlot(i) != null)
			{
				// Make a new NBT Tag Compound to write the itemstack and slot index to
				NBTTagCompound item = new NBTTagCompound();
				item.setInteger("Slot", i);
				// Writes the itemstack in slot(i) to the Tag Compound we just made
				this.getStackInSlot(i).writeToNBT(item);

				// add the tag compound to our tag list
				items.appendTag(item);
			}
		}
		// Add the TagList to the ItemStack's Tag Compound with the name "ItemInventory"
		tagcompound.setTag("ItemInventory", items);
	}
}