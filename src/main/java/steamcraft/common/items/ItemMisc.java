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
 * File created @ [Apr 23, 2014, 10:27:23 PM]
 */
package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemMisc.
 *
 * @author Decebaldecebal
 */
public class ItemMisc extends Item
{
	
	/**
	 * Instantiates a new item misc.
	 */
	public ItemMisc()
	{
		super();
		setCreativeTab(Steamcraft.tabSC2);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#registerIcons(net.minecraft.client.renderer.texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(final IIconRegister icon)
	{
		itemIcon = icon.registerIcon(LibInfo.PREFIX
				+ (getUnlocalizedName().substring(5))); // Cannot do a 'this'
														// reflection!
	}

	/**
	 * Checks if is steam powered.
	 *
	 * @return true, if is steam powered
	 */
	public boolean isSteamPowered()
	{
		return false;
	}
}
