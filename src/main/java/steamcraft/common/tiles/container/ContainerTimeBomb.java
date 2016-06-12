
package steamcraft.common.tiles.container;

import boilerplate.common.baseclasses.blocks.BaseContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
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
