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
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;

/**
 * @author warlordjones
 * 
 */
public class ContainerVanity extends Container
{

	private static final int ARMOR_START = InventoryVanity.INV_SIZE, ARMOR_END = ARMOR_START + 3, INV_START = ARMOR_END + 1,
			INV_END = INV_START + 26, HOTBAR_START = INV_END + 1, HOTBAR_END = HOTBAR_START + 8;

	public ContainerVanity(final EntityPlayer player, final InventoryPlayer inventoryPlayer, final InventoryVanity inventoryCustom)
	{
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

		for (i = 0; i < 3; ++i)
			for (int j = 0; j < 9; ++j)
				this.addSlotToContainer(new Slot(inventoryPlayer, j + (i * 9) + 9, 8 + (j * 18), 84 + (i * 18)));

		// Hotbar
		for (i = 0; i < 9; ++i)
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + (i * 18), 142));
	}

	@Override
	public boolean canInteractWith(final EntityPlayer player)
	{
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(final EntityPlayer player, final int slots)
	{
		ItemStack isCopy = null;
		final Slot slot = (Slot) this.inventorySlots.get(slots);

		if ((slot != null) && slot.getHasStack())
		{
			final ItemStack is = slot.getStack();
			isCopy = is.copy();

			// Either armor slot or custom item slot was clicked
			if (slots < INV_START)
			{
				// try to place in player inventory / action bar
				if (!this.mergeItemStack(is, INV_START, HOTBAR_END + 1, true))
					return null;

				slot.onSlotChange(is, isCopy);
			}
			else if (is.getItem() instanceof ItemBucket) // if item is our
															// custom
			// item
			{
				if (!this.mergeItemStack(is, 0, InventoryVanity.INV_SIZE, false))
					return null;
			}
			else if (is.getItem() instanceof ItemArmor) // if item is
														// armor
			{
				final int type = ((ItemArmor) is.getItem()).armorType;
				if (!this.mergeItemStack(is, ARMOR_START + type, ARMOR_START + type + 1, false))
					return null;
			}
			else if ((slots >= INV_START) && (slots < HOTBAR_START))
			{
				if (!this.mergeItemStack(is, HOTBAR_START, HOTBAR_START + 1, false))
					return null;
			}
			else if ((slots >= HOTBAR_START) && (slots < (HOTBAR_END + 1)))
				if (!this.mergeItemStack(is, INV_START, INV_END + 1, false))
					return null;

			if (is.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();

			if (is.stackSize == isCopy.stackSize)
				return null;

			slot.onPickupFromSlot(player, is);
		}

		return isCopy;
	}
}
