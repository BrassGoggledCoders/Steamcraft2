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
package steamcraft.common.tiles.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import steamcraft.common.items.InventoryPocket;

public class ContainerPocket extends Container
{
	/** The Item Inventory for this Container */
	public final InventoryPocket inventory;

	private static final int INV_SIZE = InventoryPocket.INV_SIZE;

	public ContainerPocket(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryPocket invItem)
	{
		this.inventory = invItem;

		for (int i = 0; i < INV_SIZE; ++i)
			this.addSlotToContainer(new Slot(this.inventory, i, 80 + (18 * (i / 4)), 8 + (18 * (i % 4))));

		int var3;

		for (var3 = 0; var3 < 3; ++var3)
			for (int var4 = 0; var4 < 9; ++var4)
				this.addSlotToContainer(new Slot(inventoryPlayer, var4 + (var3 * 9) + 9, 8 + (var4 * 18), 84 + (var3 * 18)));

		for (var3 = 0; var3 < 9; ++var3)
			this.addSlotToContainer(new Slot(inventoryPlayer, var3, 8 + (var3 * 18), 142));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.inventory.isUseableByPlayer(player);
	}

	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player)
	{
		// this will prevent the player from interacting with the item that
		// opened the inventory:
		if ((slot >= 0) && (this.getSlot(slot) != null) && (this.getSlot(slot).getStack() == player.getHeldItem()))
		{
			return null;
		}
		return super.slotClick(slot, button, flag, player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		return null;
	}

}
