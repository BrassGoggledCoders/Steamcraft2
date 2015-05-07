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

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import steamcraft.common.items.pda.InventoryPDA;
import steamcraft.common.items.pda.PDATabs;
import steamcraft.common.tiles.container.slot.SlotChip;

public class ContainerPDA extends Container
{
	/** The Item Inventory for this Container */
	public final InventoryPDA inventory;
	private static int tabPage = 0;
	private final int maxPages = 0;
	private static int selectedTabIndex = PDATabs.tabBlocks.getTabIndex();

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
		return this.inventory.isUseableByPlayer(player);
	}

	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player)
	{
		// this will prevent the player from interacting with the item that opened the inventory:
		if((slot >= 0) && (this.getSlot(slot) != null) && (this.getSlot(slot).getStack() == player.getHeldItem()))
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

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui()
	{
		int i = selectedTabIndex;
		selectedTabIndex = -1;
		this.setCurrentTab(PDATabs.tabArray[i]);
		int tabCount = PDATabs.tabArray.length;
	}

	private void setCurrentTab(PDATabs tabs)
	{
		if(tabs == null)
			return;
		int i = selectedTabIndex;
		selectedTabIndex = tabs.getTabIndex();
		ContainerPDA containerpda = (ContainerPDA) this.inventorySlots;

		if(i == CreativeTabs.tabInventory.getTabIndex())
		{

		}
	}

}
