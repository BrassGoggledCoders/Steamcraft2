package steamcraft.common.items;

import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.RootItem;

public class BaseItem extends RootItem
{
	String modPrefix = LibInfo.PREFIX;

	public BaseItem()
	{
		super();
		setCreativeTab(Steamcraft.tabSC2);
	}
}
