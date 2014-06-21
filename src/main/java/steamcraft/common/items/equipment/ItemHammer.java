/*
 * 
 */
package steamcraft.common.items.equipment;

import net.minecraft.item.ItemStack;
import steamcraft.common.items.BaseItem;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemHammer.
 */
public class ItemHammer extends BaseItem {
	
	/**
	 * Instantiates a new item hammer.
	 */
	public ItemHammer() {
		this.setMaxStackSize(1);
		this.setMaxDamage(ToolMaterial.IRON.getMaxUses());
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#getContainerItem(net.minecraft.item.ItemStack)
	 */
	@Override
	public ItemStack getContainerItem(ItemStack ist) {
		ist.setItemDamage(ist.getItemDamage() + 1);
		if (ist.getItemDamage() > ist.getMaxDamage())
			ist = null;

		return ist;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#hasContainerItem()
	 */
	@Override
	public boolean hasContainerItem() {
		return true;
	}

	/**
	 * If this returns true, after a recipe involving this item is crafted the
	 * container item will be added to the player's inventory instead of
	 * remaining in the crafting grid.
	 *
	 * @param par1ItemStack the par1 item stack
	 * @return true, if successful
	 */
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
		return false;
	}
}
