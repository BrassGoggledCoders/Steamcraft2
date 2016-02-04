
package steamcraft.common.items;

import net.minecraft.item.ItemStack;

/**
 * @author warlordjones
 *
 */
public class BaseItemWithMetadata extends BaseItem
{
	public BaseItemWithMetadata()
	{
		super();
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return super.getUnlocalizedName() + "." + is.getItemDamage();
	}

}
