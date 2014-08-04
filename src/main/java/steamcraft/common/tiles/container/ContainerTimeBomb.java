package steamcraft.common.tiles.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import steamcraft.common.tiles.TileTimeBomb;

public class ContainerTimeBomb extends Container
{
	TileTimeBomb tile;

	public ContainerTimeBomb(InventoryPlayer inventory, TileTimeBomb tile)
	{
		this.tile = tile;
		int var3;

		for (var3 = 0; var3 < 3; ++var3)
			for (int var4 = 0; var4 < 9; ++var4)
				addSlotToContainer(new Slot(inventory, var4 + (var3 * 9) + 9, 8 + (var4 * 18), 84 + (var3 * 18)));

		for (var3 = 0; var3 < 9; ++var3)
			addSlotToContainer(new Slot(inventory, var3, 8 + (var3 * 18), 142));
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
