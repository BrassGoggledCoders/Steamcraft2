package steamcraft.common.items;

import net.minecraft.item.ItemStack;
import steamcraft.common.Steamcraft;

public class BaseItemWithMetadata extends BaseItem
{
	public BaseItemWithMetadata()
	{
		super();
		setHasSubtypes(true);
		setCreativeTab(Steamcraft.tabSC2);
	}
	/* (non-Javadoc)
	 * @see net.minecraft.item.ItemBlock#getUnlocalizedName(net.minecraft.item.ItemStack)
	 */
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return super.getUnlocalizedName() + "." + is.getItemDamage();
	}
}
