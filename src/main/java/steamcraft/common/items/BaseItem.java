/*
 * 
 */
package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.RootItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseItem.
 */
public class BaseItem extends RootItem {
	
	/**
	 * Instantiates a new base item.
	 */
	public BaseItem() {
		super();
		setCreativeTab(Steamcraft.tabSC2);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#registerIcons(net.minecraft.client.renderer.texture.IIconRegister)
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX
				+ this.getUnlocalizedName().substring(5));
	}
}
