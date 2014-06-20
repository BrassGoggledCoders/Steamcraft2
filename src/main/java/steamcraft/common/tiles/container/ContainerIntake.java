package steamcraft.common.tiles.container;

import net.minecraft.entity.player.InventoryPlayer;
import steamcraft.common.tiles.TileIntake;

public class ContainerIntake {
	protected TileIntake tile_entity;
	private int lastSteamLevel = 0;
	private int lastWaterLevel = 0;
	public ContainerIntake(InventoryPlayer player, TileIntake tile)
	{
		this.tile_entity = tile;
	}
}
