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
 * File created @ [Feb 9, 2014, 2:31:46 PM]
 */
package common.steamcraft.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author MrArcane111
 *
 */
public class InventoryUtil {

	/** Returns double chest inventories. */
	public static IInventory getCompositeInventoryAt(IBlockAccess world, int i, int j, int k) {
		TileEntity tileEntity = world.getBlockTileEntity(i, j, k);
		
		if (tileEntity == null) {
			return null;
		}
		if (!(tileEntity instanceof IInventory)) {
			return null;
		}

		IInventory inventory = (IInventory) tileEntity;

		int bid = world.getBlockId(i, j, k);

		if (bid == Block.chest.blockID) {
			if (world.getBlockId(i - 1, j, k) == Block.chest.blockID) {
				inventory = new InventoryLargeChest("Large chest", (IInventory) world.getBlockTileEntity(i - 1, j, k), inventory);
			}
			if (world.getBlockId(i + 1, j, k) == Block.chest.blockID) {
				inventory = new InventoryLargeChest("Large chest", (IInventory) world.getBlockTileEntity(i + 1, j, k), inventory);
			}
			if (world.getBlockId(i, j, k - 1) == Block.chest.blockID) {
				inventory = new InventoryLargeChest("Large chest", (IInventory) world.getBlockTileEntity(i, j, k - 1), inventory);
			}
			if (world.getBlockId(i, j, k + 1) == Block.chest.blockID) {
				inventory = new InventoryLargeChest("Large chest", (IInventory) world.getBlockTileEntity(i, j, k + 1), inventory);
			}
		}

		return inventory;
	}

	/** Stores items into a specified slot in an inventory. */
	public static boolean storeItemInSlot(IInventory inventory, ItemStack stack, int slot) {
		if (stack == null || stack.stackSize == 0) {
			return false;
		}

		ItemStack items = inventory.getStackInSlot(slot);

		if (items == null) {
			int storedItems = stack.stackSize;
			storedItems = Math.min(storedItems, stack.getMaxStackSize());
			storedItems = Math.min(storedItems, inventory.getInventoryStackLimit());
			items = stack.splitStack(storedItems);
			inventory.setInventorySlotContents(slot, items);
			return true;
		}
		if (items.itemID == stack.itemID && items.isStackable() && (!items.getHasSubtypes() || items.getItemDamage() == stack.getItemDamage()) && items.stackSize < inventory.getInventoryStackLimit()) {
			int storedItems = stack.stackSize;
			storedItems = Math.min(storedItems, items.getMaxStackSize() - items.stackSize);
			storedItems = Math.min(storedItems, inventory.getInventoryStackLimit() - items.stackSize);
			items.stackSize += storedItems;
			stack.stackSize -= storedItems;
			return (storedItems > 0);
		}

		return false;
	}

	/** Adds a given item to an inventory, first filling used slots, then new ones. */
	public static boolean addItemStackToInventory(IInventory inventory, ItemStack stack) {
		if (!stack.isItemDamaged()) {
			int storedItems;
			
			do {
				storedItems = stack.stackSize;
				stack.stackSize = storePartialItemStack(inventory, stack);
			} while (stack.stackSize > 0 && stack.stackSize < storedItems);
			
			return stack.stackSize < storedItems;
		}
		
		int emptySlot = getFirstEmptySlot(inventory, stack);
		
		if (emptySlot >= 0) {
			inventory.setInventorySlotContents(emptySlot, ItemStack.copyItemStack(stack));
			stack.stackSize = 0;
			return true;
		}
		
		return false;
	}

	/** Adds a given item stack (all 64) to an inventory, first filling used slots, then new ones. */
	public static boolean addEntireItemStackToInventory(IInventory inventory, ItemStack stack) {
		if (!stack.isItemDamaged()) {
			int oldSize;
			
			do {
				oldSize = stack.stackSize;
				stack.stackSize = storePartialItemStack(inventory, stack);
			} while (stack.stackSize > 0 && stack.stackSize < oldSize);
			
			return stack.stackSize == 0;
		}
		
		int emptySlot = getFirstEmptySlot(inventory, stack);
		
		if (emptySlot >= 0) {
			inventory.setInventorySlotContents(emptySlot, ItemStack.copyItemStack(stack));
			stack.stackSize = 0;
			return true;
		}
		
		return false;
	}

	private static int getStackWithFreeSpace(IInventory inventory, ItemStack stack) {
		for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
			ItemStack stackAt = inventory.getStackInSlot(slot);
			
			if (stackAt != null && stackAt.itemID == stack.itemID && stackAt.isStackable() && stackAt.stackSize < stackAt.getMaxStackSize() && stackAt.stackSize < inventory.getInventoryStackLimit() && (!stackAt.getHasSubtypes() || stackAt.getItemDamage() == stack.getItemDamage())) {
				return slot;
			}
		}

		return -1;
	}

	private static int storePartialItemStack(IInventory inventory, ItemStack stack) {
		int itemID = stack.itemID;
		int stackSize = stack.stackSize;

		/** Non-stackable items */
		if (stack.getMaxStackSize() == 1) {
			int firstEmpty = getFirstEmptySlot(inventory, stack);
			
			if (firstEmpty < 0) {
				return stackSize;
			}
			if (inventory.getStackInSlot(firstEmpty) == null) {
				inventory.setInventorySlotContents(firstEmpty, ItemStack.copyItemStack(stack));
			}
			
			return 0;
		}

		int targetSlot = getStackWithFreeSpace(inventory, stack);
		
		if (targetSlot < 0) {
			targetSlot = getFirstEmptySlot(inventory, stack);
		}
		if (targetSlot < 0) {
			return stackSize;
		}
		if (inventory.getStackInSlot(targetSlot) == null) {
			inventory.setInventorySlotContents(targetSlot, new ItemStack(itemID, 0, stack.getItemDamage()));
		}

		int canStore = stackSize;

		if (canStore > inventory.getStackInSlot(targetSlot).getMaxStackSize() - inventory.getStackInSlot(targetSlot).stackSize) {
			canStore = inventory.getStackInSlot(targetSlot).getMaxStackSize() - inventory.getStackInSlot(targetSlot).stackSize;
		}
		if (canStore > inventory.getInventoryStackLimit() - inventory.getStackInSlot(targetSlot).stackSize) {
			canStore = inventory.getInventoryStackLimit() - inventory.getStackInSlot(targetSlot).stackSize;
		}
		if (canStore == 0) {
			return stackSize;
		} else {
			stackSize -= canStore;
			inventory.getStackInSlot(targetSlot).stackSize += canStore;
			return stackSize;
		}
	}

	private static int getFirstEmptySlot(IInventory inventory, ItemStack stack) {
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getStackInSlot(i) == null) {
				return i;
			}
		}

		return -1;
	}

	/** Stores whole item stacks in an inventory. */
	public static boolean storeItemInInventory(IInventory inventory, ItemStack stack) {
		if (inventory instanceof TileEntityFurnace) {
			if (isSmeltable(stack)) {
				return InventoryUtil.storeItemInSlot(inventory, stack, 0);
			} else if (isFuel(stack)) {
				return InventoryUtil.storeItemInSlot(inventory, stack, 1);
			} else {
				return false;
			}
		}
		if (inventory instanceof TileEntityBrewingStand) {
			if (stack.itemID == Item.potion.itemID) {
				if (InventoryUtil.storeItemInSlot(inventory, stack, 0)) {
					return true;
				}
				if (InventoryUtil.storeItemInSlot(inventory, stack, 1)) {
					return true;
				}
				if (InventoryUtil.storeItemInSlot(inventory, stack, 2)) {
					return true;
				}
				
				return false;
			} else {
				if (stack.getItem().isPotionIngredient()) {
					return InventoryUtil.storeItemInSlot(inventory, stack, 3);
				}
				
				return false;
			}
		}

		return InventoryUtil.addItemStackToInventory(inventory, stack);
	}
	
	public static boolean isFuel(ItemStack stack) {
		if (stack == null) {
			return false;
		}

		int itemID = stack.itemID; // TODO

		return (itemID < 256 && Block.blocksList[itemID] != null && Block.blocksList[itemID].blockMaterial == Material.wood) 
				|| (itemID == Item.stick.itemID) || (itemID == Item.coal.itemID) || (itemID == Item.bucketLava.itemID) 
				|| (itemID == Block.sapling.blockID);
	}

	public static boolean isSmeltable(ItemStack itemstack) {
		if (itemstack == null || FurnaceRecipes.smelting().getSmeltingResult(itemstack) == null) {
			return false;
		}
		
		return true;
	}

	/** Checks if a specified inventory is full. */
	public static boolean isInventoryFull(IInventory inventory) {
		if (inventory == null) {
			return false;
		}
		if (inventory instanceof TileEntityFurnace) {
			return inventory.getStackInSlot(1) != null && inventory.getStackInSlot(1).stackSize == Math.min(inventory.getInventoryStackLimit(), inventory.getStackInSlot(1).getMaxStackSize());
		}
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getStackInSlot(i) == null || inventory.getStackInSlot(i).stackSize < Math.min(inventory.getInventoryStackLimit(), inventory.getStackInSlot(i).getMaxStackSize())) {
				return false;
			}
		}
		
		return true;
	}

	/** Checks if a specified inventory has any free slots. */
	public static boolean hasInventoryNoFreeSlots(IInventory inventory) {
		if (inventory == null) {
			return false;
		}
		if (inventory instanceof TileEntityFurnace) {
			return inventory.getStackInSlot(1) != null;
		}
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getStackInSlot(i) == null) {
				return false;
			}
		}
		
		return true;
	}

	/** Checks if a specified inventory is empty. */
	public static boolean isInventoryEmpty(IInventory inventory) {
		if (inventory == null) {
			return true;
		}
		if (inventory instanceof TileEntityFurnace) {
			return inventory.getStackInSlot(1) == null;
		}
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getStackInSlot(i) != null) {
				return false;
			}
		}
		
		return true;
	}

	/** Moves stacks from one inventory to another. */
	public static boolean moveStacksForce(IInventory prevInv, IInventory newInv) {
		int copied = Math.min(prevInv.getSizeInventory(), newInv.getSizeInventory());

		for (int i = 0; i < copied; i++) {
			newInv.setInventorySlotContents(i, prevInv.getStackInSlot(i));
			prevInv.setInventorySlotContents(i, null);
		}

		return prevInv.getSizeInventory() <= newInv.getSizeInventory();
	}

	/** Moves stacks from one inventory to another but does not overwrite the transfered stacks. */
	public static void moveStacks(IInventory prevInv, IInventory newInv) {
		for (int i = 0; i < prevInv.getSizeInventory(); i++) {
			if (prevInv.getStackInSlot(i) != null) {
				addItemStackToInventory(newInv, prevInv.getStackInSlot(i));
				
				if (prevInv.getStackInSlot(i) != null && prevInv.getStackInSlot(i).stackSize <= 0) {
					prevInv.setInventorySlotContents(i, null);
				}
			}
		}
	}
	
	public static int getPlayerArmorValue(EntityPlayer player) {
		return player.inventory.getTotalArmorValue();
	}

	/**
	 * Get fuel value of a stack's item
	 * 
	 * @param stack the stack
	 * @param value value multiplier, 1.0 is the standard, furnace strength.
	 * @return fuel value
	 */
	public static int getFuelValue(ItemStack stack, double value) {
		if (stack == null) {
			return 0;
		}
		
		int itemID = stack.itemID;
		
		if (itemID == Block.wood.blockID) {
			return (int) (4 * 300 * value);
		}
		if (itemID < 256 && Block.blocksList[itemID].blockMaterial == Material.wood) {
			return (int) (300 * value);
		}
		if (itemID == Item.stick.itemID) {
			return (int) (100 * value);
		}
		if (itemID == Item.paper.itemID) {
			return (int) (150 * value);
		}
		if (itemID == Item.coal.itemID) {
			return (int) (1600 * value);
		}
		if (itemID == Item.bucketLava.itemID) {
			return (int) (20000 * value);
		}
		if (itemID == Block.sapling.blockID) {
			return (int) (100 * value);
		}
		if (itemID == Item.gunpowder.itemID) {
			return (int) (500 * value);
		}
		if (itemID == Item.blazeRod.itemID) {
			return (int) (2400 * value);
		}
		// TODO: add SC2 stuff here and whatever else...

		return (int) (GameRegistry.getFuelValue(stack) * value);
	}

	/**
	 * Go through an array of itemstacks and merge them to smallest possible
	 * count.<br>
	 * Great for inventory cleanup.
	public static ItemStack[] groupStacks(ItemStack[] input) {

		List<ItemStack> list = stacksToList(input);

		groupStacks(list);

		return stacksToArray(list);

	}

	/*
	public static void groupStacks(List<ItemStack> input) {
		if (input == null) {
			return;
		}
		for (ItemStack st1 : input) {
			if (st1 != null) {
				for (ItemStack st2 : input) {
					if (st2 != null && st2.isItemEqual(st1)) {
						int movedToFirst = Math.min(st2.stackSize, st1.getItem().setMaxStackSize() - st1.stackSize);
						
						if (movedToFirst <= 0) {
							break;
						}

						st1.stackSize += movedToFirst;
						st2.stackSize -= movedToFirst;
					}
				}
			}
		}

		ArrayList<ItemStack> copy = new ArrayList<ItemStack>(input);
		
		for (int i = copy.size() - 1; i >= 0; i--) {
			if (copy.get(i) == null || copy.get(i).stackSize <= 0) {
				input.remove(i);
			}
		}
	}
	*/

	/** Loads the entire IInventory from an NBT compound tag. */
	public static void loadInventoryFromNBT(NBTTagCompound tagCompound, String tagName, IInventory inventory) {
		NBTTagList list = tagCompound.getTagList(tagName);
		
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) list.tagAt(i);
			int slotTag = tag.getByte("Slot") & 0xff;
			
			if (slotTag >= 0 && slotTag < inventory.getSizeInventory()) {
				inventory.setInventorySlotContents(slotTag, ItemStack.loadItemStackFromNBT(tag));
			}
		}
	}

	/** Saves the entire IInventory into an NBT compound tag. */
	public static void saveInventoryToNBT(NBTTagCompound tagCompound, String tagName, IInventory inventory) {
		NBTTagList list = new NBTTagList();
		
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getStackInSlot(i) != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				inventory.getStackInSlot(i).writeToNBT(tag);
				list.appendTag(tag);
			}
		}

		tagCompound.setTag(tagName, list);
	}

	// TODO: Check BlockFurnace for correct usage.
	/** Drops inventory contents as items into the world; called by breakBlock. */ // TODO
	/*
	public static void dropInventoryContents(IInventory inventory, World world, int i, int j, int k) {
		Random random = new Random();
		
		if (inventory != null) {
			for (int l = 0; l < inventory.getSizeInventory(); l++) {
				ItemStack stack = inventory.getStackInSlot(l);

				if (stack != null) {
					float f = random.nextFloat() * 0.8F + 0.1F;
					float f1 = random.nextFloat() * 0.8F + 0.1F;
					float f2 = random.nextFloat() * 0.8F + 0.1F;

					while (stack.stackSize > 0) {
						int num = random.nextInt(21) + 10;

						if (num > stack.stackSize) {
							num = stack.stackSize;
						}

						stack.stackSize -= num;
						EntityItem entityItem = new EntityItem(world, i + f, j + f1, k + f2, new ItemStack(stack.itemID, num, stack.getItemDamage()));

						if (stack.hasTagCompound()) {
							entityItem.item.setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityItem.motionX = (float) random.nextGaussian() * f3;
						entityItem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
						entityItem.motionZ = (float) random.nextGaussian() * f3;
						world.spawnEntityInWorld(entityItem);
					}
				}
			}
		}
	}*/
}
