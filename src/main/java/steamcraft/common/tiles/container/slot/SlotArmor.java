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
package steamcraft.common.tiles.container.slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Created by Skylar on 8/29/2015.
 */
public class SlotArmor extends Slot
{
	int armortype;

	public SlotArmor(InventoryPlayer player, int slotIndex, int x, int y, int armorType)
	{
		super(player, slotIndex, x, y);
		this.armortype = armorType;
	}

	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}

	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		if(itemStack == null)
		{
			return false;
		}
		return itemStack.getItem().isValidArmor(itemStack, armortype, ((InventoryPlayer) inventory).player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getBackgroundIconIndex()
	{
		return ItemArmor.func_94602_b(armortype);
	}
}
