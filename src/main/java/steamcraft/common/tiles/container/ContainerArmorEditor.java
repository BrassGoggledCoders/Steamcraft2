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
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import steamcraft.common.tiles.TileArmorEditor;
import steamcraft.common.tiles.container.slot.SlotBrassArmor;
import steamcraft.common.tiles.container.slot.SlotModule;

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
		tileent = tile;
		int var3;

		//Armor Slot
		addSlotToContainer(new SlotBrassArmor(tileent, 0, 0, 12));

		//Input Slots Line 1
			for (int var4 = 0; var4 < 13; ++var4)
				addSlotToContainer(new SlotModule(tileent, var4 + 1, 10, var4 * 13));

		//Inv
		for (var3 = 0; var3 < 3; ++var3)
			for (int var4 = 0; var4 < 9; ++var4)
				addSlotToContainer(new Slot(player, var4 + (var3 * 9) + 9, 8 + (var4 * 18), 84 + (var3 * 18)));
		//Hotbar
		for (var3 = 0; var3 < 9; ++var3)
			addSlotToContainer(new Slot(player, var3, 8 + (var3 * 18), 142));
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
