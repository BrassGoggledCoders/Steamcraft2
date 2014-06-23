/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [3/18/14, 12:17]
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
 * The Class ItemResource.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class ItemMachinePart extends BaseItemWithMetadata
{
	// @SideOnly(Side.CLIENT)
	/** The item icon. */
	IIcon[] itemIcon = new IIcon[5];

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.item.Item#registerIcons(net.minecraft.client.renderer.texture
	 * .IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		itemIcon[0] = ir.registerIcon(LibInfo.PREFIX + "itemMagnet");
		itemIcon[1] = ir.registerIcon(LibInfo.PREFIX + "itemGenerator");
		itemIcon[2] = ir.registerIcon(LibInfo.PREFIX + "itemFan");
		itemIcon[3] = ir.registerIcon(LibInfo.PREFIX + "itemTurbine");
		itemIcon[4] = ir.registerIcon(LibInfo.PREFIX + "itemBulb");
	}

	/**
	 * Instantiates a new item resource.
	 */
	public ItemMachinePart()
	{
		super();
		setMaxStackSize(64);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.item.Item#getSubItems(net.minecraft.item.Item,
	 * net.minecraft.creativetab.CreativeTabs, java.util.List)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		for (int var4 = 0; var4 < itemIcon.length; ++var4)
		{
			l.add(new ItemStack(ConfigItems.itemMachinePart, 1, var4));
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
