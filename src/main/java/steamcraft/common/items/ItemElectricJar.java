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
 * File created @ [Jun 28, 2014, 4:58:53 PM]
 */

package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Decebaldecebal
 * 
 */
public class ItemElectricJar extends BaseElectricItem
{
	public ItemElectricJar(int maxEnergy, int maxTransfer)
	{
		super(maxEnergy, maxTransfer);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + "itemElectricJar");
	}
}
