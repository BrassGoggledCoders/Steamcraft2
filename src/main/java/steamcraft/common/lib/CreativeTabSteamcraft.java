
package steamcraft.common.lib;

import boilerplate.common.baseclasses.BaseCreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import steamcraft.common.init.InitItems;

/**
 * @author warlordjones
 *
 */
public class CreativeTabSteamcraft extends BaseCreativeTab
{
	public CreativeTabSteamcraft(String name)
	{
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return InitItems.itemBrassGoggles;
	}
}
