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
 * The Class ItemSheet.
 */
public class ItemSheet extends BaseItemWithMetadata
{
	// @SideOnly(Side.CLIENT)
	/** The item icon. */
	IIcon[] itemIcon = new IIcon[8];

	/**
	 * Instantiates a new item sheet.
	 */
	public ItemSheet()
	{
		super();
		setUnlocalizedName("itemSheet");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#getSubItems(net.minecraft.item.Item,
	 * net.minecraft.creativetab.CreativeTabs, java.util.List)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, final CreativeTabs tab, final List list)
	{
		for (int var4 = 0; var4 < 8; ++var4)
		{
			list.add(new ItemStack(ConfigItems.itemSheet, 1, var4));
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
		itemIcon[0] = ir.registerIcon(LibInfo.PREFIX + "itemSheetAluminum");
		itemIcon[1] = ir.registerIcon(LibInfo.PREFIX + "itemSheetCopper");
		itemIcon[2] = ir.registerIcon(LibInfo.PREFIX + "itemSheetTin");
		itemIcon[3] = ir.registerIcon(LibInfo.PREFIX + "itemSheetZinc");
		itemIcon[4] = ir.registerIcon(LibInfo.PREFIX + "itemSheetBrass");
		itemIcon[5] = ir.registerIcon(LibInfo.PREFIX + "itemSheetBronze");
		itemIcon[6] = ir.registerIcon(LibInfo.PREFIX + "itemSheetSteel");
		itemIcon[7] = ir.registerIcon(LibInfo.PREFIX + "itemSheetCastIron");
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
