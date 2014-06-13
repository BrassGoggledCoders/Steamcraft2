package steamcraft.common.items.equipment;

import net.minecraft.item.ItemStack;
import steamcraft.common.items.BaseItem;

public class ItemHammer extends BaseItem
{
	public ItemHammer()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(ToolMaterial.IRON.getMaxUses());
	}
	@Override
    public ItemStack getContainerItem(ItemStack ist)
	{
		ist.setItemDamage(ist.getItemDamage() + 1);
		if (ist.getItemDamage() > ist.getMaxDamage())
			ist = null;

	return ist;
    }

    @Override
    public boolean hasContainerItem() {
	return true;
    }
    /**
     * If this returns true, after a recipe involving this item is crafted the container item will be added to the
     * player's inventory instead of remaining in the crafting grid.
     */
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
    {
        return false;
    }
}
