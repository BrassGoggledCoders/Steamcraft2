
package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.items.BaseItem;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class ItemNuggetIron extends BaseItem
{
	public ItemNuggetIron()
	{
		super(Steamcraft.instance);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.PREFIX + "metals/itemNuggetIron");
	}
}
