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
package steamcraft.common.tiles;

import steamcraft.common.init.InitItems;

/**
 * @author Decebaldecebal
 *
 */
public class TileNuclearBoiler extends TileBaseBoiler
{
	@Override
	protected int getItemBurnTime()
	{

		if(this.inventory[0] == null)
			return 0;
		else if((this.inventory[0].getItem() == InitItems.itemResource) && (this.inventory[0].getItemDamage() == 5))
			return 9000;
		else
			return 0;
	}
}
