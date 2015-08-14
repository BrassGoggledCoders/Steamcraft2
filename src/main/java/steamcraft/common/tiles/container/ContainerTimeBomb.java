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
package steamcraft.common.tiles.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

import boilerplate.common.baseclasses.blocks.BaseContainer;
import steamcraft.common.tiles.TileTimeBomb;

public class ContainerTimeBomb extends BaseContainer
{
	private TileTimeBomb tileent;

	public ContainerTimeBomb(InventoryPlayer inventory, TileTimeBomb tile)
	{
		this.tileent = tile;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}

	public void updateTime(String s)
	{
		this.tileent.setTime(Integer.parseInt(s));
	}

}
