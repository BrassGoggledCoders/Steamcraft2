
package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.init.InitItems;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class ItemNugget extends BaseItemWithMetadata
{
	IIcon[] itemIcon = new IIcon[8];

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int var4 = 0; var4 < 8; ++var4)
			list.add(new ItemStack(InitItems.itemNugget, 1, var4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		for (int i = 0; i < this.itemIcon.length; i++)
			this.itemIcon[i] = ir.registerIcon(ModInfo.PREFIX + "metals/" + "itemNugget" + LibInfo.metals[i]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int itemDamage)
	{
		return this.itemIcon[itemDamage];
	}

}
