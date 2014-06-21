/*
 * 
 */
package steamcraft.common.tiles.container;

import net.minecraft.entity.player.InventoryPlayer;
import steamcraft.common.tiles.TileIntake;

// TODO: Auto-generated Javadoc
/**
 * The Class ContainerIntake.
 */
public class ContainerIntake {
	
	/** The tile_entity. */
	protected TileIntake tile_entity;
	
	/** The last steam level. */
	private int lastSteamLevel = 0;
	
	/** The last water level. */
	private int lastWaterLevel = 0;

	/**
	 * Instantiates a new container intake.
	 *
	 * @param player the player
	 * @param tile the tile
	 */
	public ContainerIntake(InventoryPlayer player, TileIntake tile) {
		this.tile_entity = tile;
	}
}
