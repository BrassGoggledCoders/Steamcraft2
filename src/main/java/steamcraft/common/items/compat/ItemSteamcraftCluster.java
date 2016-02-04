
package steamcraft.common.items.compat;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.init.InitItems;
import steamcraft.common.items.BaseItemWithMetadata;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class ItemSteamcraftCluster extends BaseItemWithMetadata
{
	IIcon[] itemIcon = new IIcon[2];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon[0] = ir.registerIcon(ModInfo.PREFIX + "metals/" + "itemClusterAluminum");
		this.itemIcon[1] = ir.registerIcon(ModInfo.PREFIX + "metals/" + "itemClusterZinc");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(InitItems.itemSteamcraftCluster, 1, 0));
		l.add(new ItemStack(InitItems.itemSteamcraftCluster, 1, 1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int itemDamage)
	{
		return this.itemIcon[itemDamage];
	}
}
