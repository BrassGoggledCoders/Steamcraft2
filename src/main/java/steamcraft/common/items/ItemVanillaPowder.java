
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
 * @author warlordjones For futureproofing
 */
public class ItemVanillaPowder extends BaseItemWithMetadata
{
	IIcon[] itemIcon = new IIcon[2];

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int var4 = 0; var4 < 2; ++var4)
			list.add(new ItemStack(InitItems.itemVanillaPowder, 1, var4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		for (int i = 0; i < this.itemIcon.length; i++)
			this.itemIcon[i] = ir.registerIcon(ModInfo.PREFIX + "metals/" + "itemPowder" + LibInfo.metalsV[i]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int itemDamage)
	{
		return this.itemIcon[itemDamage];
	}

}
