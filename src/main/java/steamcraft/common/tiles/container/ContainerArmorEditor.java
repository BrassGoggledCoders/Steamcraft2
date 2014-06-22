/*
 * 
 */
package steamcraft.common.tiles.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import steamcraft.common.tiles.TileArmorEditor;

// TODO: Auto-generated Javadoc
/**
 * The Class ContainerArmorEditor.
 */
public class ContainerArmorEditor extends Container
{

	/** The tileent. */
	protected TileArmorEditor tileent;

	/**
	 * Instantiates a new container armor editor.
	 * 
	 * @param player
	 *            the player
	 * @param tile
	 *            the tile
	 */
	public ContainerArmorEditor(InventoryPlayer player, TileArmorEditor tile)
	{
		this.tileent = tile;
		this.addSlotToContainer(new Slot(tileent, 0, 42, 52));
		this.addSlotToContainer(new Slot(tileent, 1, 132, 57));
		this.addSlotToContainer(new Slot(tileent, 2, 132, 21));

		int var3;

		for (var3 = 0; var3 < 3; ++var3)
		{
			for (int var4 = 0; var4 < 9; ++var4)
			{
				this.addSlotToContainer(new Slot(player, var4 + (var3 * 9) + 9, 8 + (var4 * 18), 84 + (var3 * 18)));
			}
		}

		for (var3 = 0; var3 < 9; ++var3)
		{
			this.addSlotToContainer(new Slot(player, var3, 8 + (var3 * 18), 142));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.inventory.Container#canInteractWith(net.minecraft.entity
	 * .player.EntityPlayer)
	 */
	@Override
	public boolean canInteractWith(EntityPlayer var1)
	{
		return true;
	}

}
