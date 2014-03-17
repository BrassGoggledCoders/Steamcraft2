/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [2 Mar 2014, 22:17:39]
 */
package common.steamcraft.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * @author warlordjones
 *
 * 2 Mar 201422:17:39
 */
public class PlayerInventoryVanity implements IInventory {
	/** The name for your custom inventory, possibly just "Inventory" */
	private final String name = "Vanity Items";

	/** In case your inventory name is too generic, define a name to store the NBT tag in as well */
	private final String tagName = "steamcraft:vanityitems";

	/** Define the inventory size here for easy reference */
	public static final int INV_SIZE = 5;

	/** Inventory's size must be same as number of slots you add to the Container class */
	ItemStack[] inventory = new ItemStack[INV_SIZE];

	public PlayerInventoryVanity()
	{
	//Empty Method
	}

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
	ItemStack stack = getStackInSlot(slot);
	if (stack != null)
	{
	if (stack.stackSize > amount)
	{
	stack = stack.splitStack(amount);
	if (stack.stackSize == 0)
	{
	setInventorySlotContents(slot, null);
	}
	}
	else
	{
	setInventorySlotContents(slot, null);
	}

	this.onInventoryChanged();
	}
	return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
	ItemStack stack = getStackInSlot(slot);
	if (stack != null)
	{
	setInventorySlotContents(slot, null);
	}
	return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
	{
	this.inventory[slot] = itemstack;

	if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
	{
	itemstack.stackSize = this.getInventoryStackLimit();
	}

	this.onInventoryChanged();
	}

	@Override
	public String getInvName()
	{
	return name;
	}

	@Override
	public boolean isInvNameLocalized()
	{
	return name.length() > 0;
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
	public void onInventoryChanged()
	{
	for (int i = 0; i < this.getSizeInventory(); ++i)
	{
	if (this.getStackInSlot(i) != null && this.getStackInSlot(i).stackSize == 0)
	this.setInventorySlotContents(i, null);
	}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
	return true;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

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

	public void writeToNBT(NBTTagCompound tagcompound)
	{
	NBTTagList nbttaglist = new NBTTagList();

	for (int i = 0; i < this.getSizeInventory(); ++i)
	{
	if (this.getStackInSlot(i) != null)
	{
	NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	nbttagcompound1.setByte("Slot", (byte) i);
	this.getStackInSlot(i).writeToNBT(nbttagcompound1);
	nbttaglist.appendTag(nbttagcompound1);
	}
	}

	// We're storing our items in a custom tag list using our 'tagName' from above
	// to prevent potential conflicts
	tagcompound.setTag(tagName, nbttaglist);
	}

	public void readFromNBT(NBTTagCompound tagcompound)
	{
	NBTTagList nbttaglist = tagcompound.getTagList(tagName);

	for (int i = 0; i < nbttaglist.tagCount(); ++i)
	{
	NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
	byte b0 = nbttagcompound1.getByte("Slot");

	if (b0 >= 0 && b0 < this.getSizeInventory())
	{
	this.setInventorySlotContents(b0, ItemStack.loadItemStackFromNBT(nbttagcompound1));
	}
	}
	}
	}
