
package steamcraft.common.tiles.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import boilerplate.common.baseclasses.blocks.BaseContainer;
import steamcraft.common.tiles.TileArmorEditor;
import steamcraft.common.tiles.container.slot.SlotArmor;
import steamcraft.common.tiles.container.slot.SlotModule;
import steamcraft.common.tiles.container.slot.SlotModuleContainer;

/**
 * @author warlordjones
 *
 */
public class ContainerArmorEditor extends BaseContainer
{
	private TileArmorEditor tileent;

	public ContainerArmorEditor(InventoryPlayer player, TileArmorEditor tile)
	{
		this.tileent = tile;
		this.setTile(this.tileent);

		for (int i = 0; i < 4; ++i)
		{
			this.addSlotToContainer(new SlotArmor(player, player.getSizeInventory() - 1 - i, 62, 8 + (i * 18), i));
		}

		this.addSlotToContainer(new SlotModuleContainer(this.tileent, 0, 89, 26));

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				this.addSlotToContainer(new SlotModule(this.tileent, j + (i * 4) + 1, 116 + (j * 18), 8 + (i * 18)));
			}
		}

		for (int var3 = 0; var3 < 3; ++var3)
		{
			for (int var4 = 0; var4 < 9; ++var4)
			{
				this.addSlotToContainer(new Slot(player, var4 + (var3 * 9) + 9, 8 + (var4 * 18), 84 + (var3 * 18)));
			}
		}

		for (int var3 = 0; var3 < 9; ++var3)
		{
			this.addSlotToContainer(new Slot(player, var3, 8 + (var3 * 18), 142));
		}
	}
}
