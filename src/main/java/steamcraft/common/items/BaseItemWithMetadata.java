/*
 *
 */
package steamcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import steamcraft.common.Steamcraft;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseItemWithMetadata.
 */
public class BaseItemWithMetadata extends BaseItem
{
	/**
	 * Instantiates a new base item with metadata.
	 */
	public BaseItemWithMetadata()
	{
		super();
		setHasSubtypes(true);
		setCreativeTab(Steamcraft.tabSC2);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.item.ItemBlock#getUnlocalizedName(net.minecraft.item.ItemStack
	 * )
	 */
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return super.getUnlocalizedName() + "." + is.getItemDamage();
	}
}
