package steamcraft.common.items.pda;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPDA extends Container
{
	/** The Item Inventory for this Container */
	public final InventoryPDA inventory;

	private static final int INV_SIZE = InventoryPDA.INV_SIZE;

	public ContainerPDA(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryPDA invItem)
	{
		this.inventory = invItem;

		for(int i = 0; i < INV_SIZE; ++i)
			this.addSlotToContainer(new SlotChip(this.inventory, i, 80 + (18 * (i / 4)), 8 + (18 * (i % 4))));
		// Hotbar
		for(int i2 = 0; i2 < 9; ++i2)
			this.addSlotToContainer(new Slot(inventoryPlayer, i2, 8 + (i2 * 18), 142));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return inventory.isUseableByPlayer(player);
	}

	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player)
	{
		// this will prevent the player from interacting with the item that opened the inventory:
		if(slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem())
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
