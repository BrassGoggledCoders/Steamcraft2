/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.items.compat;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import steamcraft.common.init.InitItems;
import steamcraft.common.items.BaseItemWithMetadata;
import steamcraft.common.lib.ModInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
