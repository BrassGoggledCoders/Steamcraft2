package steamcraft.common.tiles.container;

import steamcraft.common.tiles.TileBattery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerBattery extends Container
{
	public ContainerBattery(InventoryPlayer inventory, TileBattery tile)
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1)
	{
		return true;
	}

}
