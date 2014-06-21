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
 * File created @ [Apr 8, 2014, 12:43:08 PM]
 */
package steamcraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;

// TODO: Auto-generated Javadoc
/**
 * The Class ContainerVanity.
 * 
 * @author warlordjones
 */
public class ContainerVanity extends Container {

	/** The Constant HOTBAR_END. */
	private static final int ARMOR_START = InventoryVanity.INV_SIZE,
			ARMOR_END = ARMOR_START + 3, INV_START = ARMOR_END + 1,
			INV_END = INV_START + 26, HOTBAR_START = INV_END + 1,
			HOTBAR_END = HOTBAR_START + 8;

	/**
	 * Instantiates a new container vanity.
	 * 
	 * @param player
	 *            the player
	 * @param inventoryPlayer
	 *            the inventory player
	 * @param inventoryCustom
	 *            the inventory custom
	 */
	public ContainerVanity(final EntityPlayer player,
			final InventoryPlayer inventoryPlayer,
			final InventoryVanity inventoryCustom) {
		// Custom Slots
		// TODO: Import classes
		/*
		 * addSlotToContainer(new SlotHead(inventoryCustom, 0, 25, 8));
		 * addSlotToContainer(new SlotTunic(inventoryCustom, 1, 25, 26));
		 * addSlotToContainer(new SlotLeggings(inventoryCustom, 2, 25, 44));
		 * addSlotToContainer(new SlotBoots(inventoryCustom, 3, 25, 62));
		 * addSlotToContainer(new SlotHat(inventoryCustom, 5, 45, 8));
		 * addSlotToContainer(new SlotCape(inventoryCustom, 4, 45, 26));
		 */

		// Inventory
		int i;

		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}

		// Hotbar
		for (i = 0; i < 9; ++i) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	/**
	 * This should always return true, since custom inventory can be accessed
	 * from anywhere.
	 * 
	 * @param player
	 *            the player
	 * @return true, if successful
	 */
	@Override
	public boolean canInteractWith(final EntityPlayer player) {
		return true;
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that. Basically the same as every other
	 * container I make, since I define the same constant indices for all of
	 * them
	 * 
	 * @param player
	 *            the player
	 * @param slots
	 *            the slots
	 * @return the item stack
	 */
	@Override
	public ItemStack transferStackInSlot(final EntityPlayer player,
			final int slots) {
		ItemStack isCopy = null;
		final Slot slot = (Slot) inventorySlots.get(slots);

		if (slot != null && slot.getHasStack()) {
			final ItemStack is = slot.getStack();
			isCopy = is.copy();

			// Either armor slot or custom item slot was clicked
			if (slots < INV_START) {
				// try to place in player inventory / action bar
				if (!mergeItemStack(is, INV_START, HOTBAR_END + 1, true)) {
					return null;
				}

				slot.onSlotChange(is, isCopy);
			} else
			// Item is in inventory / hotbar, try to place either in custom or
			// armor slots
			{
				if (is.getItem() instanceof ItemBucket) // if item is our custom
														// item
				{
					if (!mergeItemStack(is, 0, InventoryVanity.INV_SIZE, false)) {
						return null;
					}
				} else if (is.getItem() instanceof ItemArmor) // if item is
																// armor
				{
					final int type = ((ItemArmor) is.getItem()).armorType;
					if (!mergeItemStack(is, ARMOR_START + type, ARMOR_START
							+ type + 1, false)) {
						return null;
					}
				} else if (slots >= INV_START && slots < HOTBAR_START) // item
																		// in
																		// player's
																		// inventory,
																		// but
																		// not
																		// in
																		// action
																		// bar
				{
					if (!mergeItemStack(is, HOTBAR_START, HOTBAR_START + 1,
							false)) // place in action bar
					{
						return null;
					}
				} else if (slots >= HOTBAR_START && slots < HOTBAR_END + 1) // item
																			// in
																			// action
																			// bar
																			// -
																			// place
																			// in
																			// player
																			// inventory
				{
					if (!mergeItemStack(is, INV_START, INV_END + 1, false)) {
						return null;
					}
				}
			}

			if (is.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (is.stackSize == isCopy.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, is);
		}

		return isCopy;
	}
}
