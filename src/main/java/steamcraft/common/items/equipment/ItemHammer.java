package steamcraft.common.items.equipment;

import steamcraft.common.items.BaseItem;

public class ItemHammer extends BaseItem
{
	public ItemHammer()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(ToolMaterial.IRON.getMaxUses());
	}
}
