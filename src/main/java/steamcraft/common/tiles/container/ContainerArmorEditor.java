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

/**
 * @author warlordjones
 *
 */
public class ContainerArmorEditor extends Container
{
	protected TileArmorEditor tileent;

	public ContainerArmorEditor(InventoryPlayer player, TileArmorEditor tile)
	{
		this.tileent = tile;
		int var3;

		this.addSlotToContainer(new SlotBrassArmor(this.tileent, 0, 8, 34));

		for(int var4 = 1; var4 < 7; ++var4)
			this.addSlotToContainer(new SlotModule(this.tileent, var4, 44 + var4 * 18, 16));
		//for(int var4 = 0; var4 < 7; ++var4)
		//	this.addSlotToContainer(new SlotModule(this.tileent, var4 + 20, 44 + var4 * 18, 34));
		//for(int var4 = 0; var4 < 7; ++var4)
		//	this.addSlotToContainer(new SlotModule(this.tileent, var4 + 30, 44 + var4 * 18, 52));

		for(var3 = 0; var3 < 3; ++var3)
			for(int var4 = 0; var4 < 9; ++var4)
				this.addSlotToContainer(new Slot(player, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
		for(var3 = 0; var3 < 9; ++var3)
			this.addSlotToContainer(new Slot(player, var3, 8 + var3 * 18, 142));
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1)
	{
		return true;
	}
}
