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
package steamcraft.common.items;

import net.minecraft.item.ItemStack;

/**
 * @author warlordjones
 * 
 */
public class ItemWithCraftingDurability extends BaseItem
{
	public ItemWithCraftingDurability()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(ToolMaterial.IRON.getMaxUses());
		this.setNoRepair();
	}

	@Override
	public ItemStack getContainerItem(ItemStack ist)
	{
		ist.setItemDamage(ist.getItemDamage() + 1);
		if(ist.getItemDamage() > ist.getMaxDamage())
			ist = null;

		return ist;
	}

	@Override
	public boolean hasContainerItem()
	{
		return true;
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
	{
		return false;
	}
}
