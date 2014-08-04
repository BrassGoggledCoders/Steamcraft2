package steamcraft.common.tiles.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import steamcraft.common.tiles.TileTimeBomb;

public class ContainerTimeBomb extends Container
{
	TileTimeBomb tile;

	public ContainerTimeBomb(InventoryPlayer inventory, TileTimeBomb tile)
	{
		this.tile = tile;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}

	public void updateTime(String s)
	{
		tile.setTime(Integer.parseInt(s));
	}

}
