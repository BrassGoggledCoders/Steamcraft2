
package steamcraft.common.tiles.container.slot;

import boilerplate.api.IEnergyItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import steamcraft.common.tiles.energy.TileBattery;

/**
 * @author decebaldecebal
 *
 */
public class SlotBattery extends Slot
{
	private TileBattery tile;

	public SlotBattery(IInventory inv, int par2, int par3, int par4)
	{
		super(inv, par2, par3, par4);

		this.tile = (TileBattery) inv;
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return stack.getItem() instanceof IEnergyItem;
	}

	@Override
	public void onSlotChange(ItemStack stack1, ItemStack stack2)
	{
		super.onSlotChange(stack1, stack2);

		this.tile.updateEnergyFromInv();
	}
}
