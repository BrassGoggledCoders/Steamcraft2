/*
 *
 */
package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemNugget.
 */
public class ItemNugget extends BaseItemWithMetadata
{

	/** The item icon. */
	IIcon[] itemIcon = new IIcon[8];

	/**
	 * Instantiates a new item nugget.
	 */
	public ItemNugget()
	{
		super();
		setUnlocalizedName("itemNugget");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.item.Item#getSubItems(net.minecraft.item.Item,
	 * net.minecraft.creativetab.CreativeTabs, java.util.List)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int var4 = 0; var4 < 8; ++var4)
		{
			list.add(new ItemStack(ConfigItems.itemNugget, 1, var4));
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * steamcraft.common.items.BaseItem#registerIcons(net.minecraft.client.renderer
	 * .texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		for(int i=0; i<itemIcon.length; i++)
		{
			itemIcon[i] = ir.registerIcon(LibInfo.PREFIX +"metals/"+"itemNugget" + LibInfo.metals[i]);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.item.Item#getIconFromDamage(int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int itemDamage)
	{
		return itemIcon[itemDamage];
	}

}
