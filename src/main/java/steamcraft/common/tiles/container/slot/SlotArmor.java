
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
		if (itemStack == null)
		{
			return false;
		}
		return itemStack.getItem().isValidArmor(itemStack, this.armortype, ((InventoryPlayer) this.inventory).player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getBackgroundIconIndex()
	{
		return ItemArmor.func_94602_b(this.armortype);
	}
}
